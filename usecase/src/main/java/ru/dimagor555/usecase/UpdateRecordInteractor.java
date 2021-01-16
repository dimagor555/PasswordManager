package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

public class UpdateRecordInteractor extends RecordInteractor implements UpdateRecord {
    private final RecordValidator validator = new RecordValidator();
    public UpdateRecordInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Record record, Callback callback) {
        if (recordRepository.containsBySiteAndLogin(record.getSite(), record.getLogin())) {
            validator.validateRecord(record);

            Record updated = recordRepository.update(record);
            callback.onRecordUpdated(updated);
        } else {
            callback.onRecordNotFound();
        }
    }
}
