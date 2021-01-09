package ru.dimagor555.presentation;

public interface Navigator {
    void openLoginWindow();

    void closeLoginWindow();

    void openMainWindow();

    void closeMainWindow();

    void openCreateWindow();

    void closeCreateWindow();

    void openUpdateWindow();

    void closeUpdateWindow();

    void openDeleteWindow();

    void closeDeleteWindow();

    void showRecordAlreadyExistsDialogError();
}
