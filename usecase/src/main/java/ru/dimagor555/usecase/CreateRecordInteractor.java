package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.domain.port.IdGenerator;
import ru.dimagor555.domain.port.RecordRepository;

public class CreateRecordInteractor extends RecordInteractor implements CreateRecord {
    private final RecordValidator validator = new RecordValidator();
    private final IdGenerator idGenerator;

    public CreateRecordInteractor(RecordRepository recordRepository, IdGenerator idGenerator) {
        super(recordRepository);
        this.idGenerator = idGenerator;
    }

    @Override
    public void execute(RecordCreationModel model, Callback callback) {
        executeMain(() -> {
            if (recordRepository.containsBySiteAndLogin(model.site(), model.login())) {
                executePost(callback::onRecordAlreadyExistError);
            } else {
                Record record = new Record(idGenerator.generate(),
                        model.site(), model.login(), model.password());
                validator.validateRecord(record);

                recordRepository.create(record);
                executePost(() -> callback.onRecordCreated(record));
            }
        });
    }
}
