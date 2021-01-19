package ru.dimagor555.javafxapp;

import ru.dimagor555.domain.port.*;
import ru.dimagor555.hasher.DefaultHasher;
import ru.dimagor555.idgenerator.SequenceIdGenerator;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.repository.HashMapRecordRepository;
import ru.dimagor555.repository.InMemoryMasterPasswordRepository;
import ru.dimagor555.usecase.*;

public class Config {
    private final RecordRepository recordRepository = new HashMapRecordRepository();
    private final MasterPasswordRepository masterPasswordRepository =
            new InMemoryMasterPasswordRepository();
    private final PasswordGeneratorFactory passGenFactory = new PasswordGeneratorFactory();
    private final IdGenerator idGenerator = new SequenceIdGenerator(recordRepository);
    private final Encryptor encryptor = null;
    private final Decryptor decryptor = null;
    private final Hasher hasher = new DefaultHasher();

    public GetAllRecords getAllRecords() {
        return new GetAllRecordsInteractor(recordRepository);
    }

    public CreateRecord createRecord() {
        return new CreateRecordInteractor(recordRepository, idGenerator);
    }

    public UpdateRecord updateRecord() {
        return new UpdateRecordInteractor(recordRepository);
    }

    public DeleteRecord deleteRecord() {
        return new DeleteRecordInteractor(recordRepository);
    }

    public Login login() {
        return new LoginInteractor(masterPasswordRepository, hasher);
    }

    public SetMasterPassword setMasterPassword() {
        return new SetMasterPasswordInteractor(masterPasswordRepository, hasher);
    }

    public PasswordGeneratorFactory getPassGenFactory() {
        return passGenFactory;
    }
}
