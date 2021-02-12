package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.Encryptor;

public class EncryptPasswordInteractor extends Interactor implements EncryptPassword {
    private final Encryptor encryptor;

    public EncryptPasswordInteractor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public void execute(String password, Callback callback) {
        executeMain(() -> {
            String encryptedPassword = encryptor.encrypt(password);
            executePost(() -> callback.onEncrypted(encryptedPassword));
        });
    }
}
