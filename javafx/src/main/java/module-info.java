module javafxapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.dimagor555.javafxapp to javafx.fxml;
    exports ru.dimagor555.javafxapp;
}