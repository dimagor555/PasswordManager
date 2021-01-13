package ru.dimagor555.javafxapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import ru.dimagor555.presentation.MainPresenter;

import java.util.Arrays;

public class MainView implements MainPresenter.View {
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView tableRecords;

    private MainPresenter presenter;

    public MainView() {

    }

    public MainView(Parent root, MainPresenter presenter) {
        btnUpdate = (Button) root.lookup("#btnUpdate");
        btnCreate = (Button) root.lookup("#btnCreate");
        btnDelete = (Button) root.lookup("#btnDelete");
        tableRecords = (TableView) root.lookup("#tableRecords");

        btnUpdate.setOnAction(event -> onUpdateBtnClicked());
        btnCreate.setOnAction(event -> onCreateBtnClicked());
        btnDelete.setOnAction(event -> onDeleteBtnClicked());

        System.out.println("Arrays.toString(tableRecords.getColumns().toArray()) = " + Arrays.toString(tableRecords.getColumns().toArray()));
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
