package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

import java.util.Collection;
import java.util.HashMap;

public class HashMapRecordRepository implements RecordRepository {
    private final HashMap<Long, Record> records = new HashMap<>();

    @Override
    public void create(Record record) {
        records.put(record.getId(), record);
    }

    @Override
    public void update(Record record) {
        var old = records.get(record.getId());
        old.setSite(record.getSite());
        old.setLogin(record.getLogin());
        old.setPassword(record.getPassword());
    }

    @Override
    public void delete(Record record) {
        records.remove(record.getId());
    }

    @Override
    public boolean containsBySiteAndLogin(Record record) {
        return getAll().parallelStream().anyMatch(record1 -> record1.equalsBySiteAndLogin(record));
    }

    @Override
    public Collection<Record> getAll() {
        return records.values();
    }
}
