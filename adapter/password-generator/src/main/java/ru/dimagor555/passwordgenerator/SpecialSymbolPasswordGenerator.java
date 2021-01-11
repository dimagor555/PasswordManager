package ru.dimagor555.passwordgenerator;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class SpecialSymbolPasswordGenerator extends AlphanumericPasswordGenerator {
    @Override
    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder(super.generatePassword(length));
        byte[] allSpecialSymbols = getAllSpecialSymbols();
        Random rand = new Random();
        for (int i = 0; i < password.length(); i++) {
            if (rand.nextFloat() < .15) {
                int random = rand.nextInt(allSpecialSymbols.length);
                password.setCharAt(i, (char) allSpecialSymbols[random]);
            }
        }
        return password.toString();
    }

    private byte[] getAllSpecialSymbols() {
        return "!@#$%^&*(){}[]".getBytes(StandardCharsets.UTF_8);
    }
}
