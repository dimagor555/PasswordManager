package ru.dimagor555.javafxapp.windows;

import javafx.scene.control.Alert;

public class AlertFactory {
    private final CssLoader cssLoader = new CssLoader();

    public Alert createErrorAlert(String header, String content) {
        return createAlert("Error", header, content);
    }

    public Alert createErrorAlert(String header) {
        return createAlert("Error", header, "");
    }

    private Alert createAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        setCss(alert);
        return alert;
    }

    private void setCss(Alert alert) {
        var dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(cssLoader.getCommonCssPath());
    }
}
