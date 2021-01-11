package ru.dimagor555.passwordgenerator;

import ru.dimagor555.domain.port.PasswordGenerator;

public class PasswordGeneratorFactory {
    public PasswordGenerator getGenerator(PasswordGeneratorType type) {
        return switch (type) {
            case ALPHANUMERIC -> new AlphanumericPasswordGenerator();
            case SPECIAL_SYMBOLS -> new SpecialSymbolPasswordGenerator();
        };
    }
}
