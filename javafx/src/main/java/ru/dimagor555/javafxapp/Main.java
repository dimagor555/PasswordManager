package ru.dimagor555.javafxapp;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.dimagor555.presentation.Navigator;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Config config = new Config();

        Navigator navigator = new WindowNavigator(config);
        navigator.openLoginWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
