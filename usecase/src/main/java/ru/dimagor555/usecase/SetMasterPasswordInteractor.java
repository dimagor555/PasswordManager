package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.MasterPassword;
import ru.dimagor555.domain.port.Hasher;
import ru.dimagor555.domain.port.MasterPasswordRepository;

import java.util.Optional;

public class SetMasterPasswordInteractor implements SetMasterPassword {
    private final MasterPasswordRepository masterPasswordRepository;
    private final Hasher hasher;

    public SetMasterPasswordInteractor(MasterPasswordRepository masterPasswordRepository, Hasher hasher) {
        this.masterPasswordRepository = masterPasswordRepository;
        this.hasher = hasher;
    }

    @Override
    public void execute(String oldPassword, String newPassword, Callback callback) {
        Optional<MasterPassword> masterPassword = masterPasswordRepository.get();
        if (masterPassword.isPresent()) {
            if (oldPassword != null && oldPassword.length() > 16
                    && oldPassword.length() < 200 && !oldPassword.isBlank()) {
                String oldPasswordHash = hasher.hash(oldPassword);
                boolean correctPassword = masterPassword.get()
                        .isPasswordHashCorrect(oldPasswordHash);
                if (correctPassword) {
                    setNewMasterPassword(newPassword);
                    callback.onPasswordSet();
                } else {
                    callback.onOldPasswordIncorrect();
                }
            }
        } else {
            setNewMasterPassword(newPassword);
            callback.onPasswordSet();
        }
    }

    @Override
    public void execute(String newPassword, Callback callback) {
        execute(null, newPassword, callback);
    }

    private void setNewMasterPassword(String newPassword) {
        String passwordHash = hasher.hash(newPassword);
        masterPasswordRepository.set(new MasterPassword(passwordHash));
    }
}
