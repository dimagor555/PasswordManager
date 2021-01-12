package ru.dimagor555.javafxapp;

import ru.dimagor555.javafxapp.windows.CreateWindow;
import ru.dimagor555.javafxapp.windows.LoginWindow;
import ru.dimagor555.javafxapp.windows.Window;
import ru.dimagor555.javafxapp.windows.WindowType;
import ru.dimagor555.passwordgenerator.PasswordGeneratorFactory;
import ru.dimagor555.presentation.CreatePresenter;
import ru.dimagor555.presentation.LoginPresenter;
import ru.dimagor555.presentation.Navigator;
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

    }

    @Override
    public void closeMainWindow() {

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

    }

    @Override
    public void closeUpdateWindow() {

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
