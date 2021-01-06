package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.PasswordRecord;

import java.util.Collection;
import java.util.Optional;

public interface PasswordRecordRepository {
    Optional<PasswordRecord> create(PasswordRecord record);

    Optional<PasswordRecord> update(PasswordRecord record);

    Optional<PasswordRecord> delete(PasswordRecord record);

    Collection<PasswordRecord> getAll();
}
