package ru.dimagor555.domain.port;

public interface Encryptor {
    void setKey(String key);

    String encrypt(String data);
}
