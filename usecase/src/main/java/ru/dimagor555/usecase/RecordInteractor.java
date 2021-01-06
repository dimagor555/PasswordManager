package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.PasswordRecordRepository;

public class RecordInteractor {
    protected final PasswordRecordRepository recordRepository;

    public RecordInteractor(PasswordRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
}
