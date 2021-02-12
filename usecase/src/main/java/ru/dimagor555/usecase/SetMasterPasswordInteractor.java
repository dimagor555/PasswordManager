package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.MasterPassword;
import ru.dimagor555.domain.port.Hasher;
import ru.dimagor555.domain.port.MasterPasswordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

import java.util.Optional;

public class SetMasterPasswordInteractor extends Interactor implements SetMasterPassword {
    private final MasterPasswordRepository masterPasswordRepository;
    private final Hasher hasher;

    public SetMasterPasswordInteractor(MasterPasswordRepository masterPasswordRepository, Hasher hasher) {
        this.masterPasswordRepository = masterPasswordRepository;
        this.hasher = hasher;
    }

    @Override
    public void execute(String oldPassword, String newPassword, Callback callback) {
        executeMain(() -> {
            Optional<MasterPassword> masterPassword = masterPasswordRepository.get();
            if (masterPassword.isPresent()) {
                if (oldPassword != null && oldPassword.length() > 16
                        && oldPassword.length() < 200 && !oldPassword.isBlank()) {
                    var password = masterPassword.get();
                    String salt = password.getSalt();
                    String oldPasswordHash = hasher.hashPassword(oldPassword, salt);
                    boolean correctPassword = password.isPasswordHashCorrect(oldPasswordHash);
                    if (correctPassword) {
                        setNewMasterPassword(newPassword, callback);
                    } else {
                        executePost(callback::onOldPasswordIncorrect);
                    }
                }
            } else {
                setNewMasterPassword(newPassword, callback);
            }
        });
    }

    @Override
    public void execute(String newPassword, Callback callback) {
        execute(null, newPassword, callback);
    }

    private void setNewMasterPassword(String newPassword, Callback callback) {
        String salt = hasher.genSalt();
        String cryptKey = hasher.hashCryptKey(newPassword, salt);
        String passwordHash = hasher.hashPassword(newPassword, salt);
        try {
            masterPasswordRepository.set(new MasterPassword(passwordHash, cryptKey, salt));
            executePost(callback::onPasswordSet);
        } catch (DatabaseException e) {
            executePost(() -> callback.onPasswordNotSet("Password not set:\n" + e.getMessage()));
        }
    }
}
