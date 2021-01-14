package ru.dimagor555.presentation;

import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.passwordgenerator.PasswordGeneratorType;
import ru.dimagor555.usecase.GeneratePassword;
import ru.dimagor555.usecase.GeneratePasswordInteractor;

public class UpdatePresenter {
    private final GeneratePassword genAlphanumPass;
    private final GeneratePassword genSpecSymPass;
    private final Navigator navigator;
    private View view;

    public UpdatePresenter(PasswordGeneratorFactory passGenFactory, Navigator navigator) {
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

    public void update() {
        // not implemented yet
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
