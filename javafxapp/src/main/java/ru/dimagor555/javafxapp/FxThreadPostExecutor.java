package ru.dimagor555.javafxapp;

import javafx.application.Platform;

import java.util.concurrent.Executor;

public class FxThreadPostExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        Platform.runLater(command);
    }
}
