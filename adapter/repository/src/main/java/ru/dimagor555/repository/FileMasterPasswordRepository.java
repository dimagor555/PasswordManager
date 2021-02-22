package ru.dimagor555.repository;

import ru.dimagor555.cryption.AesCryptor;
import ru.dimagor555.domain.entity.MasterPassword;
import ru.dimagor555.domain.port.Decryptor;
import ru.dimagor555.domain.port.Encryptor;
import ru.dimagor555.domain.port.MasterPasswordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class FileMasterPasswordRepository implements MasterPasswordRepository {
    private final static String FILE_PATH = System.getProperty("user.home")
            + "/.PasswordManager/logs";
    private static final String CRYPT_KEY = "dummykey";
    private final Encryptor encryptor;
    private final Decryptor decryptor;

    public FileMasterPasswordRepository() {
        AesCryptor dummyCryptor = new AesCryptor();
        dummyCryptor.setKey(CRYPT_KEY);
        encryptor = dummyCryptor;
        decryptor = dummyCryptor;
    }

    @Override
    public Optional<MasterPassword> get() {
        try {
            String rawInput = Files.readString(Paths.get(FILE_PATH));
            String decryptedInput = decryptor.decrypt(rawInput);
            String[] input = decryptedInput.split("\n");
            var toReturn = new MasterPassword(input[0], input[1]);
            return Optional.of(toReturn);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public void set(MasterPassword masterPassword) {
        try {
            String toWrite = masterPassword.getPasswordHash() + "\n" + masterPassword.getSalt();
            String toWriteEncrypted = encryptor.encrypt(toWrite);
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Files.writeString(Paths.get(FILE_PATH), toWriteEncrypted, StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
