package ru.dimagor555.javafxapp.views;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.presentation.MainPresenter;

import java.util.List;

public class MainView implements MainPresenter.View {
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Record> tableRecords;

    private MainPresenter presenter;

    public MainView() {

    }

    public MainView(Parent root, MainPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);
        
        btnUpdate = (Button) root.lookup("#btnUpdate");
        btnCreate = (Button) root.lookup("#btnCreate");
        btnDelete = (Button) root.lookup("#btnDelete");
        tableRecords = (TableView<Record>) root.lookup("#tableRecords");

        btnUpdate.setOnAction(event -> onUpdateBtnClicked());
        btnCreate.setOnAction(event -> onCreateBtnClicked());
        btnDelete.setOnAction(event -> onDeleteBtnClicked());

        for (TableColumn column : tableRecords.getColumns()) {
            column.setSortable(false);
            column.setReorderable(false);
        }
        tableRecords.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableRecords.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("site"));
        tableRecords.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("login"));
        tableRecords.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("password"));
    }

    private void onUpdateBtnClicked() {
        presenter.updateSelectedRecord();
    }

    private void onCreateBtnClicked() {
        presenter.createRecord();
    }

    private void onDeleteBtnClicked() {
        presenter.deleteSelectedRecord();
    }

    @Override
    public Record getSelected() {
        return tableRecords.getSelectionModel().getSelectedItem();
    }

    @Override
    public void renderRecords(List<Record> records) {
        tableRecords.getItems().clear();
        tableRecords.setItems(FXCollections.observableList(records));
    }
}
