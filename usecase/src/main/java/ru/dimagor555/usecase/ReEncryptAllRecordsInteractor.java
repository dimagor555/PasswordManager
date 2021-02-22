package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.Decryptor;
import ru.dimagor555.domain.port.Encryptor;
import ru.dimagor555.domain.port.MasterPasswordRepository;
import ru.dimagor555.domain.port.RecordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

public class ReEncryptAllRecordsInteractor extends Interactor implements ReEncryptAllRecords {
    private final RecordRepository recordRepository;
    private final RecordRepository backupRecordRepository;
    private final MasterPasswordRepository masterPasswordRepository;
    private final Decryptor decryptor;
    private final Encryptor encryptor;

    public ReEncryptAllRecordsInteractor(RecordRepository recordRepository,
                                         RecordRepository backupRecordRepository,
                                         MasterPasswordRepository masterPasswordRepository,
                                         Decryptor decryptor,
                                         Encryptor encryptor) {
        this.recordRepository = recordRepository;
        this.backupRecordRepository = backupRecordRepository;
        this.masterPasswordRepository = masterPasswordRepository;
        this.decryptor = decryptor;
        this.encryptor = encryptor;
    }

    @Override
    public void execute(String newCryptKey, Callback callback) {
        executeMain(() -> {
            recordRepository.getAll().forEach(backupRecordRepository::create);
            encryptor.setKey(newCryptKey);
            for (Record record : backupRecordRepository.getAll()) {
                String decryptedPassword = decryptor.decrypt(record.getPassword());
                String newEncryptedPassword = encryptor.encrypt(decryptedPassword);
                Record reEncryptedRecord = new Record(record.getId(), record.getSite(),
                        record.getLogin(), newEncryptedPassword);
                try {
                    recordRepository.update(reEncryptedRecord);
                } catch (DatabaseException e) {
                    rollbackReEncryption(callback);
                    executePost(() -> callback.onReEncryptionError(e.getMessage()));
                    return;
                }
            }
            decryptor.setKey(newCryptKey);
            executePost(callback::onAllRecordsReEncrypted);
        });
    }

    private void rollbackReEncryption(Callback callback) {
        try {
            backupRecordRepository.getAll().forEach(recordRepository::update);
            String oldCryptKey = masterPasswordRepository.get().get().getCryptKey();
            encryptor.setKey(oldCryptKey);
            decryptor.setKey(oldCryptKey);
        } catch (DatabaseException e) {
            executePost(() -> callback.onReEncryptionError("Error during cancelling " +
                    "re-encryption of all records:\n" + e.getMessage()));
        }
    }
}
