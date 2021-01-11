package ru.dimagor555.javafxapp.windows;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageFactory {
    public Stage createStage(WindowType type) {
        var loader = new PaneLoader();
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.loadPane(getPath(type))));
        stage.setTitle(getTitle(type));
        return stage;
    }

    private String getPath(WindowType type) {
        return "/" +  switch (type) {
            case LOGIN -> "login";
            case MAIN -> "main";
            case CREATE -> "create";
            case UPDATE -> "update";
            case DELETE -> "delete";
        } + ".fxml";
    }

    private String getTitle(WindowType type) {
        return switch (type) {
            case LOGIN -> "Login";
            case MAIN -> "Main";
            case CREATE -> "Create";
            case UPDATE -> "Update";
            case DELETE -> "Delete";
        };
    }
}
