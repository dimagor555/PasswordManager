package ru.dimagor555.javafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import ru.dimagor555.presentation.MainPresenter;

public class MainView implements MainPresenter.View {

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    private MainPresenter presenter;

    public MainView() {

    }

    public MainView(Parent root, MainPresenter presenter) {
        btnUpdate = (Button) root.lookup("#btnUpdate");
        btnCreate = (Button) root.lookup("#btnCreate");
        btnDelete = (Button) root.lookup("#btnDelete");

        btnUpdate.setOnAction(event -> onUpdateBtnClicked());
        btnCreate.setOnAction(event -> onCreateBtnClicked());
        btnDelete.setOnAction(event -> onDeleteBtnClicked());
    }

    private void onUpdateBtnClicked() {
        presenter.updateSelectedRecord();
    }

    private void onCreateBtnClicked() {
        presenter.createSelectedRecord();
    }

    private void onDeleteBtnClicked() {
        presenter.deleteSelectedRecord();
    }
}
