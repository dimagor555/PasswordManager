package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.usecase.CreateRecord;
import ru.dimagor555.usecase.RecordCreationModel;

public class CreatePresenter extends CreateUpdatePresenter {
    private final CreateRecord createRecord;
    private View view;

    public CreatePresenter(CreateRecord createRecord,
                           PasswordGeneratorFactory passGenFactory,
                           Navigator navigator) {
        super(passGenFactory, navigator);
        this.createRecord = createRecord;
    }

    public void setView(View view) {
        super.setView(view);
        this.view = view;
    }

    public void reset() {
        view.hideSiteError();
        view.hideLoginError();
        view.hidePasswordError();
        view.clearFields();
    }

    public void createRecord() {
        if (this.validateRecord()) {
            String site = view.getSite();
            String login = view.getLogin();
            String password = view.getPassword();

            RecordCreationModel toCreate = new RecordCreationModel(site, login, password);
            createRecord.execute(toCreate, new CreateRecord.Callback() {
                @Override
                public void onRecordCreated(Record record) {
                    navigator.closeCreateWindow();
                    navigator.updateMainWindow();
                }

                @Override
                public void onRecordAlreadyExistError() {
                    navigator.showRecordAlreadyExistsDialog();
                }

                @Override
                public void onDatabaseError(String message) {
                    navigator.showDatabaseErrorDialog(message);
                }
            });
        }
    }

    public void cancel() {
        navigator.closeCreateWindow();
    }

    public interface View extends CreateUpdateView {
        void clearFields();
    }
}
