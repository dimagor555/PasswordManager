package ru.dimagor555.usecase;

public interface Login {
    void execute(String password, Callback callback);

    interface Callback {
        void onSuccessfulLogin();

        void onIncorrectPassword();

        void onMasterPasswordNotFound();
    }
}
