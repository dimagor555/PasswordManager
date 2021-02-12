package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.MasterPasswordRepository;

public class HasMasterPasswordInteractor extends Interactor implements HasMasterPassword {
    private final MasterPasswordRepository masterPasswordRepository;

    public HasMasterPasswordInteractor(MasterPasswordRepository masterPasswordRepository) {
        this.masterPasswordRepository = masterPasswordRepository;
    }

    @Override
    public void execute(Callback callback) {
        executeMain(() -> {
            var masterPassword = masterPasswordRepository.get();
            if (masterPassword.isPresent()) {
                executePost(callback::onHasMasterPassword);
            } else {
                executePost(callback::onNotHasMasterPassword);
            }
        });
    }
}
