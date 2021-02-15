package ru.dimagor555.domain.port;

public interface Hasher {
    default String hashPassword(String password, String salt) {
        return hashCryptKey(hashCryptKey(password, salt), salt);
    }

    String hashCryptKey(String password, String salt);

    String genSalt();
}
