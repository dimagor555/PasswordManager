package ru.dimagor555.usecase;

public interface SetMasterPassword {
    void execute(String oldPassword, String newPassword, Callback callback);

    void execute(String newPassword, Callback callback);

    interface Callback {
        void onOldPasswordIncorrect();

        void onPasswordSet();
    }
}
