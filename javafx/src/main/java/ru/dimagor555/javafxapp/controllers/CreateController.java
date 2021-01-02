package ru.dimagor555.javafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateController {

    @FXML
    private Button btnGeneratePassword;

    @FXML
    private TextField fieldWebsite;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldPasswd;

    @FXML
    private TextField fieldPasswdLength;

    @FXML
    private CheckBox checkBoxSpecialSymbols;

    public CreateController(Pane root) {
        fieldWebsite = (TextField) root.lookup("#fieldWebsite");
        fieldEmail = (TextField) root.lookup("#fieldEmail");
        fieldPasswd = (TextField) root.lookup("#fieldPasswd");
        fieldPasswdLength = (TextField) root.lookup("#fieldPasswdLength");
        btnGeneratePassword = (Button) root.lookup("#btnGeneratePassword");
        checkBoxSpecialSymbols = (CheckBox) root.lookup("#checkBoxSpecialSymbols");

        btnGeneratePassword.setOnAction(event -> onPasswordGenerateClicked());
    }

    private void onPasswordGenerateClicked() {

    }
}
