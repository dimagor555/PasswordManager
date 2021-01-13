package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.controllers.CreateView;
import ru.dimagor555.presentation.CreatePresenter;

public class CreateWindow extends Window {
    private final CreatePresenter presenter;
    private final CreateView view;

    public CreateWindow(CreatePresenter presenter) {
        super(WindowType.CREATE);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new CreateView(root, presenter);
    }

    public CreatePresenter getPresenter() {
        return presenter;
    }

    public CreateView getView() {
        return view;
    }
}
