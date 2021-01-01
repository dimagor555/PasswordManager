package ru.dimagor555.javafxapp.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController {

    @FXML
    private TextField passwd;

    @FXML
    private Button loginBtn;

    @FXML
    private Label errorLabel;

    public LoginController(Pane root) {
        passwd = (TextField) root.lookup("#passwd");
        loginBtn = (Button) root.lookup("#loginBtn");
        errorLabel = (Label) root.lookup("#errorLabel");

        loginBtn.setOnAction(event -> loginClicked());
    }

    private void loginClicked() {
        if (passwd.getText() == "dimagor555") {
            //тут будет потом метод, вызывающий новое окно
        } else {
            errorLabel.setVisible(true);
        }
    }
}
