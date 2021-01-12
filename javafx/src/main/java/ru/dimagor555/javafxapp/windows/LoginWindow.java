package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.controllers.LoginController;
import ru.dimagor555.presentation.LoginPresenter;

public class LoginWindow extends Window {
    private final LoginPresenter presenter;
    private final LoginController view;

    public LoginWindow(LoginPresenter presenter) {
        super(WindowType.LOGIN);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new LoginController(root, presenter);
    }

    public LoginPresenter getPresenter() {
        return presenter;
    }

    public LoginController getView() {
        return view;
    }
}
