package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.usecase.UpdateRecord;

public class UpdatePresenter extends CreateUpdatePresenter {
    private final UpdateRecord updateRecord;
    private long currentRecordId;
    private View view;

    public UpdatePresenter(UpdateRecord updateRecord, PasswordGeneratorFactory passGenFactory,
                           Navigator navigator) {
        super(passGenFactory, navigator);
        this.updateRecord = updateRecord;
    }

    public void setView(View view) {
        super.setView(view);
        this.view = view;
    }

    public void updateRecord() {
        if (validateRecord()) {
            String site = view.getSite();
            String login = view.getLogin();
            String password = view.getPassword();

            Record toUpdate = new Record(currentRecordId, site, login, password);
            updateRecord.execute(toUpdate, new UpdateRecord.Callback() {
                @Override
                public void onRecordUpdated(Record record) {
                    navigator.closeUpdateWindow();
                    navigator.updateMainWindow();
                }

                @Override
                public void onRecordAlreadyExistError() {
                    navigator.showRecordAlreadyExistsDialog();
                }

                @Override
                public void onRecordNotFoundError() {
                    navigator.showRecordNotFoundDialog();
                }
            });
        }
    }

    public void reset(Record record) {
        currentRecordId = record.getId();
        view.hideSiteError();
        view.hideLoginError();
        view.hidePasswordError();
        view.showSite(record.getSite());
        view.showLogin(record.getLogin());
        view.showPassword(record.getPassword());
    }

    public interface View extends CreateUpdateView {
        void showSite(String site);

        void showLogin(String login);
    }
}
