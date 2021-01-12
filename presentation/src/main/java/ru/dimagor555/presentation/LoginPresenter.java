package ru.dimagor555.presentation;

public class LoginPresenter {
    private final Navigator navigator;
    private View view;

    public LoginPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void login() {
        String password = view.getPassword();
        if (password.equals("test")) {
            navigator.openMainWindow();
            view.hidePasswordError();
        } else {
            view.showPasswordError();
        }
    }

    public void setView(View view) { this.view = view; }

    public interface View {
        void showPasswordError();

        void hidePasswordError();

        String getPassword();
    }
}
