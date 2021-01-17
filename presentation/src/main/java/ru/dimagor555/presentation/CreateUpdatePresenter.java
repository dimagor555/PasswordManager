package ru.dimagor555.presentation;

import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.passwordgenerator.PasswordGeneratorType;
import ru.dimagor555.usecase.GeneratePassword;
import ru.dimagor555.usecase.GeneratePasswordInteractor;

public class CreateUpdatePresenter {
    private final GeneratePassword genAlphanumPass;
    private final GeneratePassword genSpecSymPass;
    protected final Navigator navigator;
    private CreateUpdateView view;

    public CreateUpdatePresenter(PasswordGeneratorFactory passGenFactory, Navigator navigator) {
        this.genAlphanumPass = new GeneratePasswordInteractor(
                passGenFactory.getGenerator(PasswordGeneratorType.ALPHANUMERIC));
        this.genSpecSymPass = new GeneratePasswordInteractor(
                passGenFactory.getGenerator(PasswordGeneratorType.SPECIAL_SYMBOLS));
        this.navigator = navigator;
    }

    public void setView(CreateUpdateView view) {
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

    public boolean validateRecord() {
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

        return !hasErrors;
    }
}
