package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.PasswordRecord;

import java.util.Optional;

public interface PasswordRecordRepository {
    Optional<PasswordRecord> create(PasswordRecord record);

    Optional<PasswordRecord> update(PasswordRecord record);

    Optional<PasswordRecord> delete(PasswordRecord record);

    Optional<PasswordRecord> get(String site, String login);
}
