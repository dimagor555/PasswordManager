package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

public class CreateRecordInteractor extends RecordInteractor implements CreateRecord {
    private final RecordValidator validator = new RecordValidator();

    public CreateRecordInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Record record, Callback callback) {
        validator.validateRecord(record);

        if (recordRepository.containsBySiteAndLogin(record)) {
            callback.onRecordAlreadyExistError();
        } else {
            recordRepository.create(record);
            callback.onRecordCreated(record);
        }
    }
}
