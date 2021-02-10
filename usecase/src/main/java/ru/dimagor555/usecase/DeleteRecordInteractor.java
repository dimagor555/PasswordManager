package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;
import ru.dimagor555.usecase.exception.DatabaseException;

public class DeleteRecordInteractor extends RecordInteractor implements DeleteRecord {
    public DeleteRecordInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Record record, Callback callback) {
        executeMain(() -> {
            try {
                recordRepository.delete(record);
                executePost(callback::onRecordDeleted);
            } catch (DatabaseException e) {
                executePost(() -> callback.onDatabaseError(e.getMessage()));
            }
        });
    }
}
