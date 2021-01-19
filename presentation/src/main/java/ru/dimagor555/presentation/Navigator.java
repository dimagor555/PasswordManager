package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;

public interface Navigator {
    void openLoginWindow();

    void closeLoginWindow();

    void openMainWindow();

    void closeMainWindow();

    void updateMainWindow();

    void openCreateWindow();

    void closeCreateWindow();

    void openUpdateWindow(Record toUpdate);

    void closeUpdateWindow();

    void openDeleteWindow(Record toDelete);

    void closeDeleteWindow();

    void openMasterPasswordWindow(boolean oldPasswordExists);

    void closeMasterPasswordWindow();

    void showRecordAlreadyExistsDialog();

    void showRecordNotFoundDialog();

    void showMasterPasswordNotFoundDialog();
}
