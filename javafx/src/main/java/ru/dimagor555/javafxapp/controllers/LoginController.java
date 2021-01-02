package ru.dimagor555.javafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController {

    @FXML
    private TextField textFieldPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label labelError;

    public LoginController() {
    }

    public LoginController(Pane root) {
        textFieldPassword = (TextField) root.lookup("#textFieldPassword");
        btnLogin = (Button) root.lookup("#btnLogin");
        labelError = (Label) root.lookup("#labelError");

        btnLogin.setOnAction(event -> onLoginClicked());
    }

    private void onLoginClicked() {

    }
}
