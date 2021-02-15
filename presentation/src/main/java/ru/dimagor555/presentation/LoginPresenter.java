package ru.dimagor555.presentation;

import ru.dimagor555.usecase.HasMasterPassword;
import ru.dimagor555.usecase.Login;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginPresenter {
    private static final long LOGIN_DELAY = 3000;

    private final Login login;
    private final HasMasterPassword hasMasterPassword;
    private final Navigator navigator;
    private View view;
    private volatile boolean loginDisabled = false;
    private final Executor loginDisableExecutor = Executors.newSingleThreadExecutor();

    public LoginPresenter(Login login, HasMasterPassword hasMasterPassword, Navigator navigator) {
        this.login = login;
        this.hasMasterPassword = hasMasterPassword;
        this.navigator = navigator;
    }

    public void reset() {
        disableLogin();
        hasMasterPassword.execute(new HasMasterPassword.Callback() {
            @Override
            public void onHasMasterPassword() {
                enableLogin();
            }

            @Override
            public void onNotHasMasterPassword() {
                navigator.showMasterPasswordNotFoundDialog();
                enableLogin();
            }
        });
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
                enableLogin();
            }
        });
        disableLogin();
        view.hidePasswordError();
    }

    public void setView(View view) { this.view = view; }

    private void disableLoginForDelay(long delay) {
        disableLogin();
        loginDisableExecutor.execute(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enableLogin();
        });
    }

    private void disableLogin() {
        loginDisabled = true;
        view.disableLogin();
    }

    private void enableLogin() {
        loginDisabled = false;
        view.enableLogin();
    }

    public interface View {
        void showPasswordError();

        void hidePasswordError();

        String getPassword();

        void disableLogin();

        void enableLogin();
    }
}
