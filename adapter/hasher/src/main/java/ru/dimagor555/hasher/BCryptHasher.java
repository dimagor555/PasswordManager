package ru.dimagor555.hasher;

import org.springframework.security.crypto.bcrypt.BCrypt;
import ru.dimagor555.domain.port.Hasher;

public class BCryptHasher implements Hasher {
    @Override
    public String hashCryptKey(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    @Override
    public String genSalt() {
        return BCrypt.gensalt(13);
    }
}
