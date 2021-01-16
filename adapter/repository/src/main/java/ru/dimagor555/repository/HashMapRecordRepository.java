package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class HashMapRecordRepository implements RecordRepository {
    private final HashMap<Long, Record> records = new HashMap<>();

    @Override
    public void create(Record record) {
        records.put(record.getId(), record);
    }

    @Override
    public Record update(Record record) {
        var old = records.get(record.getId());
        old.setSite(record.getSite());
        old.setLogin(record.getLogin());
        old.setPassword(record.getPassword());
        return old;
    }

    @Override
    public void delete(Record record) {
        records.remove(record.getId());
    }

    @Override
    public Optional<Record> getById(long id) {
        return Optional.ofNullable(records.get(id));
    }

    @Override
    public boolean containsBySiteAndLogin(String site, String login) {
        return getAll().parallelStream().anyMatch(record ->
                record.getSite().equals(site) && record.getLogin().equals(login));
    }

    @Override
    public Collection<Record> getAll() {
        return records.values();
    }
}
