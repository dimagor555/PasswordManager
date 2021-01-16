package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.Record;

import java.util.Collection;

public interface RecordRepository {
    void create(Record record);

    void update(Record record);

    void delete(Record record);

    boolean containsBySiteAndLogin(String site, String login);

    Collection<Record> getAll();
}
