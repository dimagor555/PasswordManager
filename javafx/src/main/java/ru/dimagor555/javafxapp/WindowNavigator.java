package ru.dimagor555.javafxapp;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.javafxapp.windows.*;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.presentation.*;
import ru.dimagor555.usecase.*;

import java.util.HashMap;

public class WindowNavigator implements Navigator {
    private final Config config;
    private final HashMap<WindowType, Window> windows = new HashMap<>(WindowType.values().length);

    public WindowNavigator(Config config) {
        this.config = config;
    }

    @Override
    public void openLoginWindow() {
        if (isWindowCreated(WindowType.LOGIN)) {
            openWindow(WindowType.LOGIN);
        } else {
            Login login = config.login();
            LoginPresenter presenter = new LoginPresenter(login, this);
            Window loginWindow = new LoginWindow(presenter);
            windows.put(loginWindow.getType(), loginWindow);
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
            MainPresenter presenter = new MainPresenter(getAllRecords, this);
            Window mainWindow = new MainWindow(presenter);
            windows.put(mainWindow.getType(), mainWindow);
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
            PasswordGeneratorFactory passGenFactory = config.getPassGenFactory();
            CreatePresenter presenter = new CreatePresenter(createRecord, passGenFactory, this);
            Window createWindow = new CreateWindow(presenter);
            windows.put(createWindow.getType(), createWindow);
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
            PasswordGeneratorFactory passGenFactory = config.getPassGenFactory();
            UpdatePresenter presenter = new UpdatePresenter(updateRecord, passGenFactory, this);
            Window updateWindow = new UpdateWindow(presenter);
            windows.put(updateWindow.getType(), updateWindow);
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
    public void showRecordAlreadyExistsDialog() {

    }

    @Override
    public void showRecordNotFoundDialog() {

    }

    @Override
    public void showMasterPasswordNotFoundDialog() {

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
}
