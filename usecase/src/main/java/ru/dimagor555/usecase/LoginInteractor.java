package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.Hasher;
import ru.dimagor555.domain.port.MasterPasswordRepository;

public class LoginInteractor implements Login {
    private final MasterPasswordRepository masterPasswordRepository;
    private final Hasher hasher;

    public LoginInteractor(MasterPasswordRepository masterPasswordRepository, Hasher hasher) {
        this.masterPasswordRepository = masterPasswordRepository;
        this.hasher = hasher;
    }

    @Override
    public void execute(String password, Callback callback) {
        var masterPassword = masterPasswordRepository.get();
        if (masterPassword.isPresent()) {
            String passwordHash = hasher.hash(password);
            boolean correctPassword = masterPassword.get().isPasswordHashCorrect(passwordHash);
            if (correctPassword) {
                callback.onSuccessfulLogin();
            } else {
                callback.onIncorrectPassword();
            }
        } else {
            callback.onMasterPasswordNotFound();
        }
    }
}
