package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.Decryptor;

public class DecryptPasswordInteractor extends Interactor implements DecryptPassword {
    private final Decryptor decryptor;

    public DecryptPasswordInteractor(Decryptor decryptor) {
        this.decryptor = decryptor;
    }

    @Override
    public void execute(String password, Callback callback) {
        executeMain(() -> {
            String decryptedPassword = decryptor.decrypt(password);
            executePost(() -> callback.onDecrypted(decryptedPassword));
        });
    }
}
