package ru.dimagor555.passwordgenerator;

import ru.dimagor555.domain.port.PasswordGenerator;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Random;

public class AlphanumericPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random rand = new Random();
        final byte[] allSymbols = getAllSymbols();
        for (int i = 0; i < length; i++) {
            int random = rand.nextInt(allSymbols.length);
            final String s = rand.nextBoolean()
                            ? String.valueOf((char) allSymbols[random])
                            : String.valueOf(((char) allSymbols[random])).toUpperCase(Locale.ROOT);
            password.append(s);
        }
        return password.toString();
    }

    private byte[] getAllSymbols() {
        StringBuilder allSymbols = new StringBuilder();
        for (char i = '0'; i <= '9'; i++) {
            allSymbols.append(i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            allSymbols.append(i);
        }
        return allSymbols.toString().getBytes(StandardCharsets.UTF_8);
    }
}
