package ru.dimagor555.domain.port;

public interface Encryptor {
    void setChipher(String chipher);

    String encrypt(String data);
}
