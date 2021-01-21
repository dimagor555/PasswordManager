package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

public class DeleteRecordInteractor extends RecordInteractor implements DeleteRecord {
    public DeleteRecordInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Record record, Callback callback) {
        executeMain(() -> {
            recordRepository.delete(record);
            executePost(callback::onRecordDeleted);
        });
    }
}
