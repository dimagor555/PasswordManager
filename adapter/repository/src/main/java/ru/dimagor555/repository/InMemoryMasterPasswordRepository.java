package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.MasterPassword;
import ru.dimagor555.domain.port.MasterPasswordRepository;

import java.util.Optional;

public class InMemoryMasterPasswordRepository implements MasterPasswordRepository {
    private MasterPassword instance;

    @Override
    public Optional<MasterPassword> get() {
        return Optional.ofNullable(instance);
    }

    @Override
    public void set(MasterPassword masterPassword) {
        instance = masterPassword;
    }
}
