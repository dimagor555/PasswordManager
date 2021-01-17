package ru.dimagor555.domain.entity;

public class MasterPassword {
    private final String passwordHash;

    public MasterPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isPasswordHashCorrect(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }
}
