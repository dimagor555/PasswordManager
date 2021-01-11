package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.controllers.CreateController;
import ru.dimagor555.presentation.CreatePresenter;

public class CreateWindow extends Window {
    private final CreatePresenter presenter;
    private final CreateController view;

    public CreateWindow(CreatePresenter presenter) {
        super(WindowType.CREATE);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new CreateController(root, presenter);
    }

    public CreatePresenter getPresenter() {
        return presenter;
    }

    public CreateController getView() {
        return view;
    }
}
