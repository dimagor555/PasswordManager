package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.controllers.LoginView;
import ru.dimagor555.presentation.LoginPresenter;

public class LoginWindow extends Window {
    private final LoginPresenter presenter;
    private final LoginView view;

    public LoginWindow(LoginPresenter presenter) {
        super(WindowType.LOGIN);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new LoginView(root, presenter);
    }

    public LoginPresenter getPresenter() {
        return presenter;
    }

    public LoginView getView() {
        return view;
    }
}
