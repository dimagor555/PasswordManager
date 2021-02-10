package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.Record;

import java.util.List;

public interface RecordDao {
    void create(Record record);

    void update(Record record);

    void delete(Record record);

    List<Record> findAll();
}
