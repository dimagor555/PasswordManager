package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GetAllRecordsInteractor extends RecordInteractor implements GetAllRecords {
    public GetAllRecordsInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Callback callback) {
        executeMain(() -> {
            try {
                List<Record> allRecords = new ArrayList<>(recordRepository.getAll());
                allRecords.sort(Comparator.comparing(Record::getSite)
                        .thenComparing(Record::getLogin));
                executePost(() -> callback.onAllRecordsLoaded(allRecords));
            } catch (DatabaseException e) {
                executePost(() -> callback.onDatabaseError(e.getMessage()));
            }
        });
    }
}
