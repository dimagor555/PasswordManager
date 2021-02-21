package ru.dimagor555.javafxapp.views;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Duration;
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
        tableRecords.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("site"));
        tableRecords.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("login"));

        var passwordCol = tableRecords.getColumns().get(2);
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setResizable(false);
        passwordCol.setMaxWidth(100);
        passwordCol.setMinWidth(100);
        passwordCol.setPrefWidth(100);
        makeCellsOfColumnButtons(passwordCol, root);
    }

    private <S> void makeCellsOfColumnButtons(TableColumn<Record, S> btnColumn, Parent root) {
        Callback<TableColumn<Record, S>, TableCell<Record, S>> btnCellFactory = new Callback<>() {
                    @Override
                    public TableCell<Record, S> call(TableColumn col) {
                        return new TableCell<>() {
                            @Override
                            public void updateItem(S item, boolean empty) {
                                super.updateItem(item, empty);

                                if (empty) {
                                    setText(null);
                                    setGraphic(null);
                                } else {
                                    final Button btnCopy = new Button("Copy");
                                    String encryptedPassword = (String) item;
                                    btnCopy.setOnAction(event -> {
                                        presenter.copyPassword(encryptedPassword);
                                        showCopiedTooltip(btnCopy, root);
                                        tableRecords.getSelectionModel().select(this.getTableRow().getIndex());
                                    });
                                    btnCopy.setPrefWidth(btnColumn.getPrefWidth());
                                    setGraphic(btnCopy);
                                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                                }
                            }
                        };
                    }
                };
        btnColumn.setCellFactory(btnCellFactory);
    }

    private void showCopiedTooltip(Button btn, Parent root) {
        Tooltip tooltipCopied = new Tooltip("Copied");
        tooltipCopied.setMinWidth(55);
        tooltipCopied.setMaxWidth(55);
        tooltipCopied.setPrefWidth(55);
        var scene = root.getScene();
        var window = scene.getWindow();
        Point2D p = btn.localToScene(0, 0);

        double x = p.getX() + scene.getX() + window.getX();
        x += btn.getWidth() / 2 - tooltipCopied.getPrefWidth() / 2;
        double y = p.getY() + scene.getY() + window.getY();
        y -= btn.getHeight();

        tooltipCopied.show(window, x, y);

        var pt = new PauseTransition(Duration.millis(500));
        pt.setOnFinished(event -> tooltipCopied.hide());
        pt.play();
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
