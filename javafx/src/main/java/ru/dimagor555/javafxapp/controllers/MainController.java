package ru.dimagor555.javafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    public MainController(Pane root) {
        btnUpdate = (Button) root.lookup("#btnUpdate");
        btnCreate = (Button) root.lookup("#btnCreate");
        btnDelete = (Button) root.lookup("#btnDelete");

        btnUpdate.setOnAction(event -> onUpdateBtnClicked());
        btnCreate.setOnAction(event -> onCreateBtnClicked());
        btnDelete.setOnAction(event -> onDeleteBtnClicked());
    }

    private void onUpdateBtnClicked() {

    }

    private void onCreateBtnClicked() {

    }

    private void onDeleteBtnClicked() {

    }
}
