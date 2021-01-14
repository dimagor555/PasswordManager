package ru.dimagor555.javafxapp;

import ru.dimagor555.javafxapp.windows.*;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.presentation.*;
import ru.dimagor555.usecase.CreateRecord;

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
            LoginPresenter presenter = new LoginPresenter(this);
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
            MainPresenter presenter = new MainPresenter(this);
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
    public void openCreateWindow() {
        if (isWindowCreated(WindowType.CREATE)) {
            openWindow(WindowType.CREATE);
        } else {
            CreateRecord createRecord = config.getCreateRecord();
            PasswordGeneratorFactory passGenFactory = config.getPassGenFactory();
            CreatePresenter presenter = new CreatePresenter(createRecord, passGenFactory, this);
            Window createWindow = new CreateWindow(presenter);
            windows.put(createWindow.getType(), createWindow);
            createWindow.open();
        }
    }

    @Override
    public void closeCreateWindow() {
        if (isWindowCreated(WindowType.CREATE)) {
            closeWindow(WindowType.CREATE);
        }
    }

    @Override
    public void openUpdateWindow() {
        if (isWindowCreated(WindowType.UPDATE)) {
            openWindow(WindowType.UPDATE);
        } else {
            PasswordGeneratorFactory passGenFactory = config.getPassGenFactory();
            UpdatePresenter presenter = new UpdatePresenter(passGenFactory, this);
            Window updateWindow = new UpdateWindow(presenter);
            windows.put(updateWindow.getType(), updateWindow);
            updateWindow.open();
        }
    }

    @Override
    public void closeUpdateWindow() {
        if (isWindowCreated(WindowType.UPDATE)) {
            closeWindow(WindowType.UPDATE);
        }
    }

    @Override
    public void openDeleteWindow() {

    }

    @Override
    public void closeDeleteWindow() {

    }

    @Override
    public void showRecordAlreadyExistsDialogError() {

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
