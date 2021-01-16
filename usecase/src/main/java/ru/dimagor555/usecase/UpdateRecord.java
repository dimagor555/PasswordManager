package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;

public interface UpdateRecord {
    void execute(Record record, Callback callback);

    interface Callback {
        void onRecordUpdated(Record record);

        void onRecordAlreadyExistError();

        void onRecordNotFoundError();
    }
}
