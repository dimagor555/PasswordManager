package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.PasswordRecord;
import ru.dimagor555.domain.port.PasswordRecordRepository;

public class CreateRecordInteractor extends RecordInteractor implements CreateRecord {
    private final RecordValidator validator = new RecordValidator();

    public CreateRecordInteractor(PasswordRecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(PasswordRecord record, Callback callback) {
        validator.validateRecord(record);

        if (recordRepository.get(record).isPresent()) {
            callback.onUserAlreadyExistError();
        } else {
            recordRepository.create(record);
            callback.onRecordCreated(record);
        }
    }
}
