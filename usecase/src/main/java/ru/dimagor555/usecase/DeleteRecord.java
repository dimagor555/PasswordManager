package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;

public interface DeleteRecord {
    void execute(Record record, Callback callback);

    interface Callback extends DatabaseCallback {
        void onRecordDeleted();
    }
}
