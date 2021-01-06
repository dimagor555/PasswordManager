package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.PasswordRecord;
import ru.dimagor555.domain.exception.RecordValidationException;

public class RecordValidator {
    public void validateRecord(PasswordRecord record) {
        if (record == null) {
            throw new RecordValidationException("Record should not be null");
        } else if (record.getSite() == null || record.getSite().isBlank()) {
            throw new RecordValidationException("Site should not be null");
        } else if (record.getLogin() == null || record.getLogin().isBlank()) {
            throw new RecordValidationException("Site should not be null");
        } else if (record.getPassword() == null || record.getPassword().isBlank()) {
            throw new RecordValidationException("Site should not be null");
        }
    }
}
