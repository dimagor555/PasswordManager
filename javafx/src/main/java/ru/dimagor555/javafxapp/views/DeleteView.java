package ru.dimagor555.javafxapp.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import ru.dimagor555.presentation.DeletePresenter;

public class DeleteView implements DeletePresenter.View {
    @FXML
    private Text text;

    @FXML
    private Button btnAccept;

    @FXML
    private Button btnCancel;

    private DeletePresenter presenter;

    public DeleteView() {
    }

    public DeleteView(Parent root, DeletePresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        text = (Text) root.lookup("#text");
        btnAccept = (Button) root.lookup("#btnAccept");
        btnCancel = (Button) root.lookup("#btnCancel");

        btnAccept.setOnAction(event -> onAcceptBtnClicked());
        btnCancel.setOnAction(event -> onCancelBtnClicked());
    }

    private void onAcceptBtnClicked() {
        presenter.acceptDelete();
    }

    private void onCancelBtnClicked() {
        presenter.cancelDelete();
    }

    @Override
    public void setText(String text) {
        this.text.setText(text);
    }
}
