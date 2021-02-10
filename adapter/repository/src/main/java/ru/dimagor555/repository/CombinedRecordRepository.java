package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

import java.util.Collection;
import java.util.Optional;

public class CombinedRecordRepository implements RecordRepository {
    private final HashMapRecordRepository repository = new HashMapRecordRepository();
    private final RecordDao dao;

    private boolean dbLoaded = false;

    public CombinedRecordRepository(RecordDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(Record record) {
        try {
            dao.create(record);
            repository.create(record);
        } catch (Exception e) {
            throw new DatabaseException("Create: " + e.getMessage());
        }
    }

    @Override
    public Record update(Record record) {
        try {
            dao.update(record);
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
                var records = dao.findAll();
                records.forEach(repository::create);
                dbLoaded = true;
            } catch (Exception e) {
                throw new DatabaseException("FindAll: " + e.getMessage());
            }
        }
        return repository.getAll();
    }
}
