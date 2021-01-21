package ru.dimagor555.javafxapp;

import ru.dimagor555.domain.port.*;
import ru.dimagor555.hasher.DefaultHasher;
import ru.dimagor555.idgenerator.SequenceIdGenerator;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.repository.HashMapRecordRepository;
import ru.dimagor555.repository.InMemoryMasterPasswordRepository;
import ru.dimagor555.usecase.*;

import java.util.concurrent.Executor;

public class Config {
    private final RecordRepository recordRepository = new HashMapRecordRepository();
    private final MasterPasswordRepository masterPasswordRepository =
            new InMemoryMasterPasswordRepository();
    private final PasswordGeneratorFactory passGenFactory = new PasswordGeneratorFactory();
    private final IdGenerator idGenerator = new SequenceIdGenerator(recordRepository);
    private final Encryptor encryptor = null;
    private final Decryptor decryptor = null;
    private final Hasher hasher = new DefaultHasher();

    private final Executor mainExecutor = new MultiThreadMainExecutor();
    private final Executor postExecutor = new FxThreadPostExecutor();

    public GetAllRecords getAllRecords() {
        var interactor = new GetAllRecordsInteractor(recordRepository);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public CreateRecord createRecord() {
        var interactor = new CreateRecordInteractor(recordRepository, idGenerator);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public UpdateRecord updateRecord() {
        var interactor = new UpdateRecordInteractor(recordRepository);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public DeleteRecord deleteRecord() {
        var interactor = new DeleteRecordInteractor(recordRepository);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public Login login() {
        var interactor = new LoginInteractor(masterPasswordRepository, hasher);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public SetMasterPassword setMasterPassword() {
        var interactor = new SetMasterPasswordInteractor(masterPasswordRepository, hasher);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public PasswordGeneratorFactory getPassGenFactory() {
        return passGenFactory;
    }
}
