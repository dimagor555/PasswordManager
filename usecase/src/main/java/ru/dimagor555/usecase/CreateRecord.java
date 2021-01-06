package ru.dimagor555.usecase;

import ru.dimagor555.domain.entity.PasswordRecord;

public interface CreateRecord {
    void execute(PasswordRecord record, Callback callback);

    interface Callback {
        void onRecordCreated(PasswordRecord record);

        void onUserAlreadyExistError();
    }
}
