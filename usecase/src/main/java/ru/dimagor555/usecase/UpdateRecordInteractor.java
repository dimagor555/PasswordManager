package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.RecordRepository;

import java.util.Optional;

public class UpdateRecordInteractor extends RecordInteractor implements UpdateRecord {
    private final RecordValidator validator = new RecordValidator();
    public UpdateRecordInteractor(RecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Record record, Callback callback) {
        Optional<Record> updatable = recordRepository.getById(record.getId());
        if (updatable.isPresent()) {
            Optional<Record> duplicate = recordRepository.getBySiteAndLogin(record.getSite(), record.getLogin());
            boolean isNotDuplicate = duplicate.isEmpty() || updatable.get().equals(duplicate.get());
            if (isNotDuplicate) {
                validator.validateRecord(record);

                Record updated = recordRepository.update(record);
                callback.onRecordUpdated(updated);
            } else {
                callback.onRecordAlreadyExistError();
            }
        } else {
            callback.onRecordNotFoundError();
        }
    }
}
