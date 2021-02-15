package ru.dimagor555.domain.entity;

public class MasterPassword {
    private final String passwordHash;
    private String cryptKey;
    private final String salt;

    public MasterPassword(String passwordHash, String salt) {
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public MasterPassword(String passwordHash, String cryptKey, String salt) {
        this.passwordHash = passwordHash;
        this.cryptKey = cryptKey;
        this.salt = salt;
    }

    public boolean isPasswordHashCorrect(String passwordHash) {
        return this.passwordHash.equals(passwordHash);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getCryptKey() {
        return cryptKey;
    }

    public void setCryptKey(String cryptKey) {
        this.cryptKey = cryptKey;
    }

    public String getSalt() {
        return salt;
    }
}
