package ru.dimagor555.presentation;

import ru.dimagor555.usecase.SetMasterPassword;

public class MasterPasswordPresenter {
    private final SetMasterPassword setMasterPassword;
    private final Navigator navigator;
    private boolean oldPasswordExistsMode;
    private boolean setQueried = false;
    private View view;

    public MasterPasswordPresenter(SetMasterPassword setMasterPassword, Navigator navigator) {
        this.setMasterPassword = setMasterPassword;
        this.navigator = navigator;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void reset(boolean oldPasswordExists) {
        enable();
        oldPasswordExistsMode = oldPasswordExists;
        if (oldPasswordExistsMode) {
            view.showOldPasswordField();
        } else {
            view.hideOldPasswordField();
        }
        view.hideOldPasswordIncorrectError();
        view.hideNewPasswordLengthError();
        view.hideNewPasswordDoNotMatchesError();
        view.clearFields();
    }

    public void setMasterPassword() {
        if (setQueried) {
            return;
        }
        disable();

        hideAllErrors();
        String newPassword1 = view.getNewPassword1();
        String newPassword2 = view.getNewPassword2();

        boolean hasErrors = false;

        if (newPassword1.length() < 16 || newPassword1.length() > 200 || newPassword1.isBlank()) {
            view.showNewPasswordLengthError();
            hasErrors = true;
        }

        if (!newPassword1.equals(newPassword2)) {
            view.showNewPasswordDoNotMatchesError();
            hasErrors = true;
        }

        if (!hasErrors) {
            var callback = new SetMasterPassword.Callback() {
                @Override
                public void onOldPasswordIncorrect() {
                    view.showOldPasswordIncorrectError();
                    enable();
                }

                @Override
                public void onPasswordSet() {
                    navigator.closeMasterPasswordWindow();
                }

                @Override
                public void onPasswordNotSet(String message) {
                    navigator.showDatabaseErrorDialog(message);
                    enable();
                }
            };

            if (oldPasswordExistsMode) {
                String oldPassword = view.getOldPassword();
                setMasterPassword.execute(oldPassword, newPassword1, callback);
            } else {
                setMasterPassword.execute(newPassword1, callback);
            }
        } else {
            enable();
        }
    }

    public void cancel() {
        navigator.closeMasterPasswordWindow();
    }

    private void hideAllErrors() {
        view.hideOldPasswordIncorrectError();
        view.hideNewPasswordLengthError();
        view.hideNewPasswordDoNotMatchesError();
    }

    private void disable() {
        setQueried = true;
        view.disableButtons();
    }

    private void enable() {
        setQueried = false;
        view.enableButtons();
    }

    public interface View {
        void showOldPasswordField();

        void hideOldPasswordField();

        void showOldPasswordIncorrectError();

        void hideOldPasswordIncorrectError();

        void showNewPasswordLengthError();

        void hideNewPasswordLengthError();

        void showNewPasswordDoNotMatchesError();

        void hideNewPasswordDoNotMatchesError();

        String getOldPassword();

        String getNewPassword1();

        String getNewPassword2();

        void disableButtons();

        void enableButtons();

        void clearFields();
    }
}
