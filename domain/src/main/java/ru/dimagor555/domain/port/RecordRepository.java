package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.Record;

import java.util.Collection;
import java.util.Optional;

public interface RecordRepository {
    void create(Record record);

    void update(Record record);

    void delete(Record record);

    Optional<Record> get(Record record);

    Collection<Record> getAll();
}
