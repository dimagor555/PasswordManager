package ru.dimagor555.javafxapp.create;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateController {

    @FXML
    private Button btnPasswdGenerator;

    @FXML
    private TextField fieldWebsite;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldPasswd;

    @FXML
    private TextField fieldPasswdLength;

    @FXML
    private CheckBox boxSim;

    public CreateController(Pane root) {
        fieldWebsite = (TextField) root.lookup("#fieldWebsite");
        fieldEmail = (TextField) root.lookup("#fieldEmail");
        fieldPasswd = (TextField) root.lookup("#fieldPasswd");
        fieldPasswdLength = (TextField) root.lookup("#fieldPasswdLength");
        btnPasswdGenerator = (Button) root.lookup("#btnPasswdGenerator");
        boxSim = (CheckBox) root.lookup("#boxSim");

        btnPasswdGenerator.setOnAction(event -> gererate());
        boxSim.setOnAction(event -> specialSymbol());
    }

    private void gererate() {

    }

    private void specialSymbol() {

    }
}
