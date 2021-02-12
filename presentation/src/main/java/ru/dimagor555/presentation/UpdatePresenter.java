package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.usecase.DecryptPassword;
import ru.dimagor555.usecase.EncryptPassword;
import ru.dimagor555.usecase.UpdateRecord;

public class UpdatePresenter extends CreateUpdatePresenter {
    private final UpdateRecord updateRecord;
    private final EncryptPassword encryptPassword;
    private final DecryptPassword decryptPassword;
    private long currentRecordId;
    private View view;

    public UpdatePresenter(UpdateRecord updateRecord,
                           EncryptPassword encryptPassword, DecryptPassword decryptPassword,
                           PasswordGeneratorFactory passGenFactory,
                           Navigator navigator) {
        super(passGenFactory, navigator);
        this.updateRecord = updateRecord;
        this.encryptPassword = encryptPassword;
        this.decryptPassword = decryptPassword;
    }

    public void setView(View view) {
        super.setView(view);
        this.view = view;
    }

    public void reset(Record record) {
        currentRecordId = record.getId();
        view.hideSiteError();
        view.hideLoginError();
        view.hidePasswordError();
        view.showSite(record.getSite());
        view.showLogin(record.getLogin());
        view.showPassword(record.getPassword());
        decryptPassword.execute(record.getPassword(), decryptedPassword ->
                view.showPassword(decryptedPassword));
    }

    public void updateRecord() {
        if (validateRecord()) {
            String site = view.getSite();
            String login = view.getLogin();
            String password = view.getPassword();

            encryptPassword.execute(password, encryptedPassword ->
                    executeUpdateRecord(site, login, encryptedPassword));
        }
    }

    private void executeUpdateRecord(String site, String login, String encryptedPassword) {
        Record toUpdate = new Record(currentRecordId, site, login, encryptedPassword);
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

            @Override
            public void onDatabaseError(String message) {
                navigator.showDatabaseErrorDialog(message);
            }
        });
    }

    public void cancel() {
        navigator.closeUpdateWindow();
    }

    public interface View extends CreateUpdateView {
        void showSite(String site);

        void showLogin(String login);
    }
}
