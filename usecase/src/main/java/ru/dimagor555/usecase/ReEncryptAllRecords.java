package ru.dimagor555.usecase;

public interface ReEncryptAllRecords {
    void execute(String newCryptKey, Callback callback);

    interface Callback {
        void onAllRecordsReEncrypted();

        void onReEncryptionError(String message);
    }
}
