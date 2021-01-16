package ru.dimagor555.javafxapp.views;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import ru.dimagor555.presentation.CreatePresenter;

public class CreateView implements CreatePresenter.View {

    @FXML
    private Button btnGeneratePassword;

    @FXML
    private Button btnCreate;

    @FXML
    private TextField fieldSite;

    @FXML
    private TextField fieldLogin;

    @FXML
    private TextArea fieldPassword;

    @FXML
    private ChoiceBox<Integer> chBoxPasswordLen;

    @FXML
    private CheckBox checkBoxSpecialSymbols;

    @FXML
    private Label lblSiteError;

    @FXML
    private Label lblLoginError;

    @FXML
    private Label lblPasswordError;

    private CreatePresenter presenter;

    public CreateView() {

    }

    public CreateView(Parent root, CreatePresenter presenter) {
        this.presenter = presenter;
        presenter.setView(this);

        btnGeneratePassword = (Button) root.lookup("#btnGeneratePassword");
        btnGeneratePassword.setOnAction(event -> onPasswordGenerateClicked());
        btnCreate = (Button) root.lookup("#btnCreate");
        btnCreate.setOnAction(event -> onCreateClicked());

        fieldSite = (TextField) root.lookup("#fieldSite");
        fieldLogin = (TextField) root.lookup("#fieldLogin");
        fieldPassword = (TextArea) root.lookup("#fieldPassword");

        chBoxPasswordLen = (ChoiceBox<Integer>) root.lookup("#chBoxPasswordLen");
        var items = FXCollections.observableArrayList(8, 12, 16, 24, 32, 48, 64, 72, 100);
        chBoxPasswordLen.setItems(items);
        chBoxPasswordLen.setValue(items.get(2));

        checkBoxSpecialSymbols = (CheckBox) root.lookup("#checkBoxSpecialSymbols");

        lblSiteError = (Label) root.lookup("#lblSiteError");
        lblLoginError = (Label) root.lookup("#lblLoginError");
        lblPasswordError = (Label) root.lookup("#lblPasswordError");
    }

    private void onPasswordGenerateClicked() {
        presenter.generatePassword();
    }

    private void onCreateClicked() {
        presenter.create();
    }

    @Override
    public void showSiteError() {
        lblSiteError.setVisible(true);
    }

    @Override
    public void hideSiteError() {
        lblSiteError.setVisible(false);
    }

    @Override
    public void showLoginError() {
        lblLoginError.setVisible(true);
    }

    @Override
    public void hideLoginError() {
        lblLoginError.setVisible(false);
    }

    @Override
    public void showPasswordError() {
        lblPasswordError.setVisible(true);
    }

    @Override
    public void hidePasswordError() {
        lblPasswordError.setVisible(false);
    }

    @Override
    public void showPassword(String password) {
        fieldPassword.setText(password);
    }

    @Override
    public String getSite() {
        return fieldSite.getText();
    }

    @Override
    public String getLogin() {
        return fieldLogin.getText();
    }

    @Override
    public String getPassword() {
        return fieldPassword.getText();
    }

    @Override
    public int getPasswordLength() {
        return chBoxPasswordLen.getValue();
    }

    @Override
    public boolean isSpecialSymbolsSelected() {
        return checkBoxSpecialSymbols.isSelected();
    }
}
