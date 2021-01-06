package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.PasswordRecordRepository;

public class GetAllRecordsInteractor extends RecordInteractor implements GetAllRecords {
    public GetAllRecordsInteractor(PasswordRecordRepository recordRepository) {
        super(recordRepository);
    }

    @Override
    public void execute(Callback callback) {
        callback.onAllRecordsLoaded(recordRepository.getAll());
    }
}
