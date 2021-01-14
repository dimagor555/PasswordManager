package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.controllers.UpdateView;
import ru.dimagor555.presentation.UpdatePresenter;

public class UpdateWindow extends Window {
    private final UpdatePresenter presenter;
    private final UpdateView view;

    public UpdateWindow(UpdatePresenter presenter) {
        super(WindowType.UPDATE);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new UpdateView(root, presenter);
    }

    public UpdatePresenter getPresenter() {
        return presenter;
    }

    public UpdateView getView() {
        return view;
    }
}
