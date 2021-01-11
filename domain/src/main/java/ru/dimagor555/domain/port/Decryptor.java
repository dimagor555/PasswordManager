package ru.dimagor555.domain.port;

public interface Decryptor {
    void setChipher(String chipher);

    String decrypt(String data);
}
