package ru.dimagor555.repository;

import ru.dimagor555.domain.entity.MasterPassword;
import ru.dimagor555.domain.port.MasterPasswordRepository;

import java.util.Optional;

public class CombinedMasterPasswordRepository implements MasterPasswordRepository {
    private final MasterPasswordRepository inMemoryRepository =
            new InMemoryMasterPasswordRepository();
    private final MasterPasswordRepository fileRepository = new FileMasterPasswordRepository();

    @Override
    public Optional<MasterPassword> get() {
        if (inMemoryRepository.get().isEmpty()) {
            var password = fileRepository.get();
            if (password.isPresent()) {
                inMemoryRepository.set(password.get());
            } else {
                inMemoryRepository.set(null);
            }
        }
        return inMemoryRepository.get();
    }

    @Override
    public void set(MasterPassword masterPassword) {
        fileRepository.set(masterPassword);
        inMemoryRepository.set(masterPassword);
    }
}
