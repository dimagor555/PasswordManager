package ru.dimagor555.javafxapp.windows;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageFactory {
    private final CssLoader cssLoader = new CssLoader();

    public Stage createStage(WindowType type) {
        var loader = new PaneLoader();
        Stage stage = new Stage();
        Scene scene = new Scene(loader.loadPane(getFxmlPath(type)));
        setCss(scene, type);
        stage.setScene(scene);
        stage.setTitle(getTitle(type));
        return stage;
    }

    private void setCss(Scene scene, WindowType type) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(cssLoader.getCommonCssPath());
        var windowStyleSheet = cssLoader.getCssPath(getFileName(type));
        windowStyleSheet.ifPresent(s -> scene.getStylesheets().add(s));
    }

    private String getFxmlPath(WindowType type) {
        return "/fxml/" +  getFileName(type) + ".fxml";
    }

    private String getFileName(WindowType type) {
        return switch (type) {
            case LOGIN -> "login";
            case MAIN -> "main";
            case CREATE -> "create";
            case UPDATE -> "update";
            case DELETE -> "delete";
            case MASTER_PASSWORD -> "masterpassword";
        };
    }

    private String getTitle(WindowType type) {
        return switch (type) {
            case LOGIN -> "Login";
            case MAIN -> "Password manager";
            case CREATE -> "Create";
            case UPDATE -> "Update";
            case DELETE -> "Delete";
            case MASTER_PASSWORD -> "Set master password";
        };
    }
}
