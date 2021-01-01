package ru.dimagor555.javafxapp.delete;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DeleteController {

    @FXML
    private Button btnYesDel;

    @FXML
    private Button btnNoDel;

    public DeleteController(Pane root) {
        btnYesDel = (Button) root.lookup("#btnYesDel");
        btnNoDel = (Button) root.lookup("#btnNoDel");

        btnYesDel.setOnAction(event -> yes());
        btnNoDel.setOnAction(event -> no());
    }

    private void yes() {

    }

    private void no() {

    }
}
