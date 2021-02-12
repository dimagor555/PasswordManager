package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.Decryptor;
import ru.dimagor555.domain.port.Encryptor;

public class RecordCryptor {
    private final Encryptor encryptor;
    private final Decryptor decryptor;

    public RecordCryptor(Encryptor encryptor, Decryptor decryptor) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
    }

    public Record encryptRecord(Record record) {
        final long id = record.getId();
        String encryptedSite = encryptor.encrypt(record.getSite());
        String encryptedLogin = encryptor.encrypt(record.getLogin());
        String encryptedPassword = record.getPassword();
        return new Record(id, encryptedSite, encryptedLogin, encryptedPassword);
    }

    public Record decryptRecord(Record record) {
        final long id = record.getId();
        String site = decryptor.decrypt(record.getSite());
        String login = decryptor.decrypt(record.getLogin());
        String encryptedPassword = record.getPassword();
        return new Record(id, site, login, encryptedPassword);
    }
}
