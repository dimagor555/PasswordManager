package ru.dimagor555.javafxapp.windows;

import javafx.stage.Stage;

public class Window {
    private final WindowType type;
    private final Stage stage;

    public Window(WindowType type) {
        this.type = type;
        StageFactory stageFactory = new StageFactory();
        stage = stageFactory.createStage(type);
    }

    public void open() {
        stage.show();
    }

    public void close() {
        stage.close();
    }

    public WindowType getType() {
        return type;
    }

    public Stage getStage() {
        return stage;
    }
}
