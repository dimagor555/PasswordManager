package ru.dimagor555.javafxapp;

import ru.dimagor555.clipboard.JavafxClipboard;
import ru.dimagor555.cryption.AesCryptor;
import ru.dimagor555.cryption.AesCryptorAutoSetKey;
import ru.dimagor555.dbdao.RecordHibernateDao;
import ru.dimagor555.domain.port.*;
import ru.dimagor555.hasher.BCryptHasher;
import ru.dimagor555.idgenerator.SequenceIdGenerator;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.repository.CombinedMasterPasswordRepository;
import ru.dimagor555.repository.CombinedRecordRepository;
import ru.dimagor555.usecase.*;

import java.util.concurrent.Executor;

public class Config {
    private final MasterPasswordRepository masterPasswordRepository =
            new CombinedMasterPasswordRepository();

    private final AesCryptor aesCryptor = new AesCryptorAutoSetKey(
            new GetCryptKeyInteractor(masterPasswordRepository));
    private final Encryptor encryptor = aesCryptor;
    private final Decryptor decryptor = aesCryptor;
    private final Hasher hasher = new BCryptHasher();

    private final RecordHibernateDao recordDao = new RecordHibernateDao();
    private final RecordRepository recordRepository
            = new CombinedRecordRepository(recordDao, encryptor, decryptor);
    private final PasswordGeneratorFactory passGenFactory = new PasswordGeneratorFactory();
    private final IdGenerator idGenerator = new SequenceIdGenerator(recordRepository);
    private final Clipboard clipboard = new JavafxClipboard();

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

    public HasMasterPassword hasMasterPassword() {
        var interactor = new HasMasterPasswordInteractor(masterPasswordRepository);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public SetMasterPassword setMasterPassword() {
        var interactor = new SetMasterPasswordInteractor(masterPasswordRepository, hasher);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public EncryptPassword encryptPassword() {
        var interactor = new EncryptPasswordInteractor(encryptor);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public DecryptPassword decryptPassword() {
        var interactor = new DecryptPasswordInteractor(decryptor);
        interactor.buildInteractor(mainExecutor, postExecutor);
        return interactor;
    }

    public PutInClipboard putInClipboard() {
        var interactor = new PutInClipboardInteractor(clipboard);
        interactor.buildInteractor(postExecutor, postExecutor);
        return interactor;
    }

    public PasswordGeneratorFactory getPassGenFactory() {
        return passGenFactory;
    }
}
