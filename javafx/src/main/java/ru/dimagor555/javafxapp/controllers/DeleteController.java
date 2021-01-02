package ru.dimagor555.javafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DeleteController {

    @FXML
    private Button btnAccept;

    @FXML
    private Button btnCancel;

    public DeleteController(Pane root) {
        btnAccept = (Button) root.lookup("#btnAccept");
        btnCancel = (Button) root.lookup("#btnCancel");

        btnAccept.setOnAction(event -> onAcceptBtnClicked());
        btnCancel.setOnAction(event -> onCancelBtnClicked());
    }

    private void onAcceptBtnClicked() {

    }

    private void onCancelBtnClicked() {

    }
}
