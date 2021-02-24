package ru.dimagor555.javafxapp.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import ru.dimagor555.presentation.MasterPasswordPresenter;

public class MasterPasswordView implements MasterPasswordPresenter.View {
    @FXML
    private Button btnSet;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblOldPassword;
    
    @FXML
    private PasswordField fieldOldPassword;

    @FXML
    private PasswordField fieldNewPassword1;

    @FXML
    private PasswordField fieldNewPassword2;

    @FXML
    private Label lblPasswordIncorrect;

    @FXML
    private Label lblPasswordLengthError;

    @FXML
    private Label lblPasswordNotMatchError;
    
    private MasterPasswordPresenter presenter;

    public MasterPasswordView() {
    }

    public MasterPasswordView(Parent root, MasterPasswordPresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        lblOldPassword = (Label) root.lookup("#lblOldPassword");
        fieldOldPassword = (PasswordField) root.lookup("#fieldOldPassword");
        fieldNewPassword1 = (PasswordField) root.lookup("#fieldNewPassword1");
        fieldNewPassword2 = (PasswordField) root.lookup("#fieldNewPassword2");

        lblPasswordIncorrect = (Label) root.lookup("#lblPasswordIncorrect");
        lblPasswordLengthError = (Label) root.lookup("#lblPasswordLengthError");
        lblPasswordNotMatchError = (Label) root.lookup("#lblPasswordNotMatchError");

        btnSet = (Button) root.lookup("#btnSet");
        btnCancel = (Button) root.lookup("#btnCancel");
        btnSet.setOnAction(event -> onSetBtnClicked());
        btnCancel.setOnAction(event -> onCancelBtnClicked());
    }

    private void onSetBtnClicked() {
        presenter.setMasterPassword();
    }

    private void onCancelBtnClicked() {
        presenter.cancel();
    }

    @Override
    public void showOldPasswordField() {
        lblOldPassword.setVisible(true);
        fieldOldPassword.setVisible(true);
    }

    @Override
    public void hideOldPasswordField() {
        lblOldPassword.setVisible(false);
        fieldOldPassword.setVisible(false);
    }

    @Override
    public void showOldPasswordIncorrectError() {
        lblPasswordIncorrect.setVisible(true);
    }

    @Override
    public void hideOldPasswordIncorrectError() {
        lblPasswordIncorrect.setVisible(false);
    }

    @Override
    public void showNewPasswordLengthError() {
        lblPasswordLengthError.setVisible(true);
    }

    @Override
    public void hideNewPasswordLengthError() {
        lblPasswordLengthError.setVisible(false);
    }

    @Override
    public void showNewPasswordDoNotMatchesError() {
        lblPasswordNotMatchError.setVisible(true);
    }

    @Override
    public void hideNewPasswordDoNotMatchesError() {
        lblPasswordNotMatchError.setVisible(false);
    }

    @Override
    public String getOldPassword() {
        return fieldOldPassword.getText();
    }

    @Override
    public String getNewPassword1() {
        return fieldNewPassword1.getText();
    }

    @Override
    public String getNewPassword2() {
        return fieldNewPassword2.getText();
    }

    @Override
    public void disableButtons() {
        btnSet.setDisable(true);
        btnCancel.setDisable(true);
    }

    @Override
    public void enableButtons() {
        btnSet.setDisable(false);
        btnCancel.setDisable(false);
    }

    @Override
    public void clearFields() {
        fieldOldPassword.clear();
        fieldNewPassword1.clear();
        fieldNewPassword2.clear();
    }
}
