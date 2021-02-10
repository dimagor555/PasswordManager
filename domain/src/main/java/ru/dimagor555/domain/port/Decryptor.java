package ru.dimagor555.domain.port;

public interface Decryptor {
    void setKey(String key);

    String decrypt(String data);
}
