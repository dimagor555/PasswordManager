package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.Hasher;
import ru.dimagor555.domain.port.MasterPasswordRepository;

public class LoginInteractor extends Interactor implements Login {
    private final MasterPasswordRepository masterPasswordRepository;
    private final Hasher hasher;

    public LoginInteractor(MasterPasswordRepository masterPasswordRepository, Hasher hasher) {
        this.masterPasswordRepository = masterPasswordRepository;
        this.hasher = hasher;
    }

    @Override
    public void execute(String password, Callback callback) {
        executeMain(() -> {
            var masterPassword = masterPasswordRepository.get();
            if (masterPassword.isPresent()) {
                var masterPass = masterPassword.get();
                String passwordHash = hasher.hashPassword(password, masterPass.getSalt());
                boolean correctPassword = masterPass.isPasswordHashCorrect(passwordHash);
                if (correctPassword) {
                    masterPass.setCryptKey(hasher.hashCryptKey(password, masterPass.getSalt()));
                    executePost(callback::onSuccessfulLogin);
                } else {
                    executePost(callback::onIncorrectPassword);
                }
            } else {
                executePost(callback::onMasterPasswordNotFound);
            }
        });
    }
}
