package ru.dimagor555.usecase;

public interface EncryptPassword {
    void execute(String password, Callback callback);

    interface Callback {
        void onEncrypted(String encryptedPassword);
    }
}
