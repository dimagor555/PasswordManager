package ru.dimagor555.presentation;

import ru.dimagor555.usecase.Login;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginPresenter {
    private static final long LOGIN_DELAY = 3000;

    private final Login login;
    private final Navigator navigator;
    private View view;
    private volatile boolean loginDisabled = false;
    private final Executor loginDisableExecutor = Executors.newSingleThreadExecutor();


    public LoginPresenter(Login login, Navigator navigator) {
        this.login = login;
        this.navigator = navigator;
    }

    public void login() {
        if (loginDisabled) {
            return;
        }
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
                disableLoginForDelay(LOGIN_DELAY);
            }

            @Override
            public void onMasterPasswordNotFound() {
                navigator.showMasterPasswordNotFoundDialog();
            }
        });
    }

    public void setView(View view) { this.view = view; }

    private void disableLoginForDelay(long delay) {
        loginDisabled = true;
        view.disableLogin();
        loginDisableExecutor.execute(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            loginDisabled = false;
            view.enableLogin();
        });
    }

    public interface View {
        void showPasswordError();

        void hidePasswordError();

        String getPassword();

        void disableLogin();

        void enableLogin();
    }
}
