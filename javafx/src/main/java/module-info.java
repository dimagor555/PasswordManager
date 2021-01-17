module javafxapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires domain;
    requires usecase;
    requires presentation;
    requires repository;
    requires passwordgenerator;
    requires idgenerator;
    requires hasher;

    opens ru.dimagor555.javafxapp to javafx.fxml;
    opens ru.dimagor555.javafxapp.views to javafx.fxml;
    opens ru.dimagor555.javafxapp.windows to javafx.fxml;

    exports ru.dimagor555.javafxapp;
    exports ru.dimagor555.javafxapp.views;
    exports ru.dimagor555.javafxapp.windows;
}