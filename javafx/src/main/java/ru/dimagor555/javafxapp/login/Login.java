package ru.dimagor555.javafxapp.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.dimagor555.javafxapp.login.LoginController;

public class Login extends Application {

    public static final String LOGIN_PANE = "/login.fxml";

    public static LoginController loginController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        var path = getClass().getResource(LOGIN_PANE);
        Pane root = FXMLLoader.load(path);
        loginController = new LoginController(root);
        Scene scene = new Scene(root);

        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Password Manager");
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
