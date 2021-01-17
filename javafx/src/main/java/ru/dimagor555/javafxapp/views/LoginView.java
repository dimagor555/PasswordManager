package ru.dimagor555.javafxapp.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.dimagor555.presentation.LoginPresenter;

public class LoginView implements LoginPresenter.View {
    @FXML
    private TextField textFieldPassword;

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

        textFieldPassword = (TextField) root.lookup("#textFieldPassword");
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
    }

    @Override
    public void hidePasswordError() {
        labelError.setVisible(false);
    }

    @Override
    public String getPassword() {
        return textFieldPassword.getText();
    }
}
