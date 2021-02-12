package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.Decryptor;
import ru.dimagor555.domain.port.Encryptor;
import ru.dimagor555.domain.port.RecordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CombinedRecordRepository implements RecordRepository {
    private final HashMapRecordRepository repository = new HashMapRecordRepository();
    private final RecordDao dao;
    private final RecordCryptor recordCryptor;

    private boolean dbLoaded = false;

    public CombinedRecordRepository(RecordDao dao, Encryptor encryptor, Decryptor decryptor) {
        this.dao = dao;
        this.recordCryptor = new RecordCryptor(encryptor, decryptor);
    }

    @Override
    public void create(Record record) {
        try {
            Record toCreateInDb = recordCryptor.encryptRecord(record);
            dao.create(toCreateInDb);
            repository.create(record);
        } catch (Exception e) {
            throw new DatabaseException("Create: " + e.getMessage());
        }
    }

    @Override
    public Record update(Record record) {
        try {
            Record toUpdateInDb = recordCryptor.encryptRecord(record);
            dao.update(toUpdateInDb);
            return repository.update(record);
        } catch (Exception e) {
            throw new DatabaseException("Update: " + e.getMessage());
        }
    }

    @Override
    public void delete(Record record) {
        try {
            dao.delete(record);
            repository.delete(record);
        } catch (Exception e) {
            throw new DatabaseException("Delete: " + e.getMessage());
        }
    }

    @Override
    public Optional<Record> getById(long id) {
        return repository.getById(id);
    }

    @Override
    public Optional<Record> getBySiteAndLogin(String site, String login) {
        return repository.getBySiteAndLogin(site, login);
    }

    @Override
    public Collection<Record> getAll() {
        if (!dbLoaded) {
            try {
                var encryptedRecords = dao.findAll();
                List<Record> decryptedRecords = new ArrayList<>(encryptedRecords.size());
                encryptedRecords.forEach(record ->
                        decryptedRecords.add(recordCryptor.decryptRecord(record)));
                decryptedRecords.forEach(repository::create);
                dbLoaded = true;
            } catch (Exception e) {
                throw new DatabaseException("FindAll: " + e.getMessage());
            }
        }
        return repository.getAll();
    }
}
