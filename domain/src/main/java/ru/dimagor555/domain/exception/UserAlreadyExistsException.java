package ru.dimagor555.domain.exception;

import ru.dimagor555.domain.entity.PasswordRecord;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(PasswordRecord record) {
        super(String.format("Record for site '%s' and login '%s' is already exists",
                record.getSite(), record.getLogin()));
    }
}
