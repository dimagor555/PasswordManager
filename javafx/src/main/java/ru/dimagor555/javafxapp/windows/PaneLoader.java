package ru.dimagor555.javafxapp.windows;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PaneLoader {
    public Pane loadPane(String path) {
        try {
            return FXMLLoader.load(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pane();
    }
}
