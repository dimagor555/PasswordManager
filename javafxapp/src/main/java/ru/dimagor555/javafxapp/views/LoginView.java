package ru.dimagor555.javafxapp.views;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import ru.dimagor555.presentation.LoginPresenter;

public class LoginView implements LoginPresenter.View {
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label labelError;

    private LoginPresenter presenter;

    public LoginView() {
    }

    public LoginView(Parent root, LoginPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        textFieldPassword = (PasswordField) root.lookup("#textFieldPassword");
        btnLogin = (Button) root.lookup("#btnLogin");
        labelError = (Label) root.lookup("#labelError");

        btnLogin.setOnAction(event -> onLoginClicked());
    }

    private void onLoginClicked() {
        presenter.login();
    }

    @Override
    public void showPasswordError() {
        labelError.setVisible(true);
        FadeTransition ft = new FadeTransition(new Duration(300), labelError);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.play();
    }

    @Override
    public void hidePasswordError() {
        labelError.setVisible(false);
    }

    @Override
    public String getPassword() {
        return textFieldPassword.getText();
    }

    @Override
    public void disableLogin() {
        btnLogin.setDisable(true);
    }

    @Override
    public void enableLogin() {
        btnLogin.setDisable(false);
    }
}
