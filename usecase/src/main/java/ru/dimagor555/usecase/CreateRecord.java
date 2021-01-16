package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;

public interface CreateRecord {
    void execute(RecordCreationModel model, Callback callback);

    interface Callback {
        void onRecordCreated(Record record);

        void onRecordAlreadyExistError();
    }
}
