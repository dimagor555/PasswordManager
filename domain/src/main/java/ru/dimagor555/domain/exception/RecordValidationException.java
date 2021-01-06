package ru.dimagor555.domain.exception;

public class RecordValidationException extends RuntimeException {
    public RecordValidationException(String message) {
        super(message);
    }
}
