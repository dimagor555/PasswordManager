package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.PasswordGenerator;

public class GeneratePasswordInteractor implements GeneratePassword {
    private final PasswordGenerator passwordGenerator;

    public GeneratePasswordInteractor(PasswordGenerator passwordGenerator) {
        this.passwordGenerator = passwordGenerator;
    }

    @Override
    public String execute(int length) {
        return passwordGenerator.generatePassword(length);
    }
}
