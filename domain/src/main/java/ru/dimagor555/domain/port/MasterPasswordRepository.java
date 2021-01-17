package ru.dimagor555.domain.port;

import ru.dimagor555.domain.entity.MasterPassword;

import java.util.Optional;

public interface MasterPasswordRepository {
    Optional<MasterPassword> get();

    void set(MasterPassword masterPassword);
}
