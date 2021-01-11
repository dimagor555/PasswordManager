package ru.dimagor555.javafxapp;

import ru.dimagor555.domain.port.Decryptor;
import ru.dimagor555.domain.port.Encryptor;
import ru.dimagor555.domain.port.Hasher;
import ru.dimagor555.domain.port.RecordRepository;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.repository.HashMapRecordRepository;
import ru.dimagor555.usecase.CreateRecord;
import ru.dimagor555.usecase.CreateRecordInteractor;

public class Config {
    private final RecordRepository recordRepository = new HashMapRecordRepository();
    private final PasswordGeneratorFactory passGenFactory = new PasswordGeneratorFactory();
    private final Encryptor encryptor = null;
    private final Decryptor decryptor = null;
    private final Hasher hasher = null;

    public CreateRecord getCreateRecord() {
        return new CreateRecordInteractor(recordRepository);
    }

    public PasswordGeneratorFactory getPassGenFactory() {
        return passGenFactory;
    }
}
