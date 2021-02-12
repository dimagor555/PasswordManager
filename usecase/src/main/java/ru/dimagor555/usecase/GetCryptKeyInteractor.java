package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.MasterPasswordRepository;

public class GetCryptKeyInteractor implements GetCryptKey {
    private final MasterPasswordRepository masterPasswordRepository;

    public GetCryptKeyInteractor(MasterPasswordRepository masterPasswordRepository) {
        this.masterPasswordRepository = masterPasswordRepository;
    }

    @Override
    public String execute() {
        var masterPassword = masterPasswordRepository.get();
        if (masterPassword.isPresent()) {
            return masterPassword.get().getCryptKey();
        } else {
            throw new IllegalStateException();
        }
    }
}
