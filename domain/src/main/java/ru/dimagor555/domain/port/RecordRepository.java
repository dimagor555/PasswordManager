package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.Record;

import java.util.Collection;
import java.util.Optional;

public interface RecordRepository {
    void create(Record record);

    Record update(Record record);

    void delete(Record record);

    Optional<Record> getById(long id);

    Optional<Record> getBySiteAndLogin(String site, String login);

    default boolean containsBySiteAndLogin(String site, String login) {
        return getBySiteAndLogin(site, login).isPresent();
    }

    Collection<Record> getAll();
}
