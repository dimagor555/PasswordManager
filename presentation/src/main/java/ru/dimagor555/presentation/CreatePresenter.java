package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.passwordgenerator.PasswordGeneratorType;
import ru.dimagor555.usecase.CreateRecord;
import ru.dimagor555.usecase.GeneratePassword;
import ru.dimagor555.usecase.GeneratePasswordInteractor;

public class CreatePresenter {
    private final CreateRecord createRecord;
    private final GeneratePassword genAlphanumPass;
    private final GeneratePassword genSpecSymPass;
    private final Navigator navigator;
    private View view;

    public CreatePresenter(CreateRecord createRecord,
                           PasswordGeneratorFactory passGenFactory,
                           Navigator navigator) {
        this.createRecord = createRecord;
        this.genAlphanumPass = new GeneratePasswordInteractor(
                passGenFactory.getGenerator(PasswordGeneratorType.ALPHANUMERIC));
        this.genSpecSymPass = new GeneratePasswordInteractor(
                passGenFactory.getGenerator(PasswordGeneratorType.SPECIAL_SYMBOLS));
        this.navigator = navigator;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void generatePassword() {
        int passwordLen = view.getPasswordLength();
        boolean specialSymbols = view.isSpecialSymbolsSelected();
        String password;
        if (specialSymbols) {
            password = genSpecSymPass.execute(passwordLen);
        } else {
            password = genAlphanumPass.execute(passwordLen);
        }
        view.showPassword(password);
    }

    public void create() {
        String site = view.getSite();
        String login = view.getLogin();
        String password = view.getPassword();

        boolean hasErrors = false;

        if (site.length() < 2 || site.length() > 50) {
            view.showSiteError();
            hasErrors = true;
        } else {
            view.hideSiteError();
        }

        if (login.length() < 2 || login.length() > 50) {
            view.showLoginError();
            hasErrors = true;
        } else {
            view.hideLoginError();
        }

        if (password.length() < 8 || password.length() > 100) {
            view.showPasswordError();
            hasErrors = true;
        } else {
            view.hidePasswordError();
        }

        if (!hasErrors) {
            Record toCreate = new Record(1, site, login, password);
            createRecord.execute(toCreate, new CreateRecord.Callback() {
                @Override
                public void onRecordCreated(Record record) {
                    navigator.closeCreateWindow();
                }

                @Override
                public void onRecordAlreadyExistError() {
                    navigator.showRecordAlreadyExistsDialogError();
                }
            });
        }
    }

    public interface View {
        void showSiteError();

        void hideSiteError();

        void showLoginError();

        void hideLoginError();

        void showPasswordError();

        void hidePasswordError();

        void showPassword(String password);

        String getSite();

        String getLogin();

        String getPassword();

        int getPasswordLength();

        boolean isSpecialSymbolsSelected();
    }
}
