package ru.dimagor555.javafxapp;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.javafxapp.windows.*;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.presentation.*;
import ru.dimagor555.usecase.*;

import java.util.HashMap;

public class WindowNavigator implements Navigator {
    private final Config config;
    private final HashMap<WindowType, Window> windows = new HashMap<>(WindowType.values().length);
    private final AlertFactory alertFactory = new AlertFactory();

    public WindowNavigator(Config config) {
        this.config = config;
    }

    @Override
    public void openLoginWindow() {
        if (isWindowCreated(WindowType.LOGIN)) {
            openWindow(WindowType.LOGIN);
        } else {
            Login login = config.login();
            HasMasterPassword hasMasterPassword = config.hasMasterPassword();
            LoginPresenter presenter = new LoginPresenter(login, hasMasterPassword, this);
            Window loginWindow = new LoginWindow(presenter);
            loginWindow.getStage().setResizable(false);
            windows.put(loginWindow.getType(), loginWindow);
            presenter.reset();
            loginWindow.open();
        }
    }

    @Override
    public void closeLoginWindow() {
        if (isWindowCreated(WindowType.LOGIN)) {
            closeWindow(WindowType.LOGIN);
        }
    }

    @Override
    public void openMainWindow() {
        if (isWindowCreated(WindowType.MAIN)) {
            openWindow(WindowType.MAIN);
        } else {
            GetAllRecords getAllRecords = config.getAllRecords();
            DecryptPassword decryptPassword = config.decryptPassword();
            PutInClipboard putInClipboard = config.putInClipboard();
            MainPresenter presenter = new MainPresenter(getAllRecords, decryptPassword, putInClipboard, this);
            Window mainWindow = new MainWindow(presenter);
            windows.put(mainWindow.getType(), mainWindow);
            presenter.update();
            mainWindow.open();
        }
    }

    @Override
    public void closeMainWindow() {
        if (isWindowCreated(WindowType.MAIN)) {
            closeWindow(WindowType.MAIN);
        }
    }

    @Override
    public void updateMainWindow() {
        if (isWindowCreated(WindowType.MAIN)) {
            MainWindow mainWindow = (MainWindow) windows.get(WindowType.MAIN);
            mainWindow.getPresenter().update();
        }
    }

    @Override
    public void openCreateWindow() {
        if (isWindowCreated(WindowType.CREATE)) {
            CreateWindow createWindow = (CreateWindow) windows.get(WindowType.CREATE);
            createWindow.getPresenter().reset();
            createWindow.open();
        } else {
            CreateRecord createRecord = config.createRecord();
            EncryptPassword encryptPassword = config.encryptPassword();
            PasswordGeneratorFactory passGenFactory = config.getPassGenFactory();
            CreatePresenter presenter = new CreatePresenter(
                    createRecord, encryptPassword, passGenFactory, this);
            Window createWindow = new CreateWindow(presenter);
            windows.put(createWindow.getType(), createWindow);
            setOwnerForWindow(WindowType.MAIN, WindowType.CREATE);
            openCreateWindow();
        }
    }

    @Override
    public void closeCreateWindow() {
        if (isWindowCreated(WindowType.CREATE)) {
            closeWindow(WindowType.CREATE);
        }
    }

    @Override
    public void openUpdateWindow(Record toUpdate) {
        if (isWindowCreated(WindowType.UPDATE)) {
            UpdateWindow updateWindow = (UpdateWindow) windows.get(WindowType.UPDATE);
            updateWindow.getPresenter().reset(toUpdate);
            updateWindow.open();
        } else {
            UpdateRecord updateRecord = config.updateRecord();
            EncryptPassword encryptPassword = config.encryptPassword();
            DecryptPassword decryptPassword = config.decryptPassword();
            PasswordGeneratorFactory passGenFactory = config.getPassGenFactory();
            UpdatePresenter presenter = new UpdatePresenter(updateRecord, encryptPassword,
                    decryptPassword, passGenFactory, this);
            Window updateWindow = new UpdateWindow(presenter);
            windows.put(updateWindow.getType(), updateWindow);
            setOwnerForWindow(WindowType.MAIN, WindowType.UPDATE);
            openUpdateWindow(toUpdate);
        }
    }

    @Override
    public void closeUpdateWindow() {
        if (isWindowCreated(WindowType.UPDATE)) {
            closeWindow(WindowType.UPDATE);
        }
    }

    @Override
    public void openDeleteWindow(Record toDelete) {
        if (isWindowCreated(WindowType.DELETE)) {
            DeleteWindow deleteWindow = (DeleteWindow) windows.get(WindowType.DELETE);
            deleteWindow.getPresenter().reset(toDelete);
            deleteWindow.open();
        } else {
            DeleteRecord deleteRecord = config.deleteRecord();
            DeletePresenter presenter = new DeletePresenter(deleteRecord, this);
            Window deleteWindow = new DeleteWindow(presenter);
            windows.put(deleteWindow.getType(), deleteWindow);
            setOwnerForWindow(WindowType.MAIN, WindowType.DELETE);
            openDeleteWindow(toDelete);
        }
    }

    @Override
    public void closeDeleteWindow() {
        if (isWindowCreated(WindowType.DELETE)) {
            closeWindow(WindowType.DELETE);
        }
    }

    @Override
    public void openMasterPasswordWindow(boolean oldPasswordExists) {
        if (isWindowCreated(WindowType.MASTER_PASSWORD)) {
            var passwordWindow = (MasterPasswordWindow) windows.get(WindowType.MASTER_PASSWORD);
            passwordWindow.getPresenter().reset(oldPasswordExists);
            passwordWindow.open();
        } else {
            SetMasterPassword setMasterPassword = config.setMasterPassword();
            var presenter = new MasterPasswordPresenter(setMasterPassword, this);
            Window passwordWindow = new MasterPasswordWindow(presenter);
            windows.put(passwordWindow.getType(), passwordWindow);
            passwordWindow.getStage().initModality(Modality.APPLICATION_MODAL);
            openMasterPasswordWindow(oldPasswordExists);
        }
    }

    @Override
    public void closeMasterPasswordWindow() {
        if (isWindowCreated(WindowType.MASTER_PASSWORD)) {
            closeWindow(WindowType.MASTER_PASSWORD);
        }
    }

    @Override
    public void showRecordAlreadyExistsDialog() {
        Alert alert = alertFactory.createErrorAlert(
                "Record for this site and login already exists");
        alert.showAndWait();
    }

    @Override
    public void showRecordNotFoundDialog() {
        Alert alert = alertFactory.createErrorAlert("Record not found");
        alert.showAndWait();
    }

    @Override
    public void showMasterPasswordNotFoundDialog() {
        Alert alert = alertFactory.createErrorAlert(
                "Master password not found",
                "Do you want to set master password?");
        alert.getButtonTypes().addAll(ButtonType.CANCEL);

        var result = alert.showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                openMasterPasswordWindow(false);
            }
        });
    }

    @Override
    public void showDatabaseErrorDialog(String message) {
        Alert alert = alertFactory.createErrorAlert("Database error", message);
        alert.showAndWait();
    }

    private boolean isWindowCreated(WindowType type) {
        return windows.containsKey(type);
    }

    private void closeWindow(WindowType type) {
        windows.get(type).close();
    }

    private void openWindow(WindowType type) {
        windows.get(type).open();
    }

    private void setOwnerForWindow(WindowType ownerType, WindowType childType) {
        Window owner = windows.get(ownerType);
        Window child = windows.get(childType);

        child.getStage().initOwner(owner.getStage().getOwner());
        child.getStage().initModality(Modality.APPLICATION_MODAL);
    }
}
