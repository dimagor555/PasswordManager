package ru.dimagor555.usecase;

public interface DecryptPassword {
    void execute(String password, Callback callback);

    interface Callback {
        void onDecrypted(String decryptedPassword);
    }
}
