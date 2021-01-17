package ru.dimagor555.presentation;

import ru.dimagor555.usecase.Login;

public class LoginPresenter {
    private final Login login;
    private final Navigator navigator;
    private View view;

    public LoginPresenter(Login login, Navigator navigator) {
        this.login = login;
        this.navigator = navigator;
    }

    public void login() {
        String password = view.getPassword();
        login.execute(password, new Login.Callback() {
            @Override
            public void onSuccessfulLogin() {
                navigator.openMainWindow();
                navigator.closeLoginWindow();
            }

            @Override
            public void onIncorrectPassword() {
                view.showPasswordError();
            }

            @Override
            public void onMasterPasswordNotFound() {
                navigator.showMasterPasswordNotFoundDialog();
            }
        });
    }

    public void setView(View view) { this.view = view; }

    public interface View {
        void showPasswordError();

        void hidePasswordError();

        String getPassword();
    }
}
