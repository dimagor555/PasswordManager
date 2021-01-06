package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.PasswordRecord;

import java.util.Collection;
import java.util.Optional;

public interface PasswordRecordRepository {
    void create(PasswordRecord record);

    void update(PasswordRecord record);

    void delete(PasswordRecord record);

    Optional<PasswordRecord> get(PasswordRecord record);

    Collection<PasswordRecord> getAll();
}
