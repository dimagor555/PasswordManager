package ru.dimagor555.javafxapp.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertFactory {
    private final CssLoader cssLoader = new CssLoader();

    private Alert createAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        setCss(alert);
        return alert;
    }

    public Alert createErrorAlert(String header, String content) {
        var alert = createAlert("Error", header, content);
        alert.getButtonTypes().add(ButtonType.OK);
        return alert;
    }

    public Alert createErrorAlert(String header) {
        return createErrorAlert(header, "");
    }

    private void setCss(Alert alert) {
        var dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(cssLoader.getCommonCssPath());
    }
}
