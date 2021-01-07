package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.Record;

import java.util.Collection;

public interface GetAllRecords {
    void execute(Callback callback);

    interface Callback {
        void onAllRecordsLoaded(Collection<Record> allRecords);
    }
}
