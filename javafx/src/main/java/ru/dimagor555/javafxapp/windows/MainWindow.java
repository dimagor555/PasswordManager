package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.controllers.MainView;
import ru.dimagor555.presentation.MainPresenter;

public class MainWindow extends Window {
    private final MainPresenter presenter;
    private final MainView view;

    public MainWindow(MainPresenter presenter) {
        super(WindowType.MAIN);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new MainView(root, presenter);
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public MainView getView() { return view;}
}
