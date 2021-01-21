package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.RecordRepository;

public class RecordInteractor extends Interactor {
    protected final RecordRepository recordRepository;

    public RecordInteractor(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
}
