package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

import java.util.Collection;

public class GetAllRecordsInteractor extends RecordInteractor implements GetAllRecords {
    public GetAllRecordsInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Callback callback) {
        executeMain(() -> {
            Collection<Record> allRecords = recordRepository.getAll();
            executePost(() -> callback.onAllRecordsLoaded(allRecords));
        });
    }
}
