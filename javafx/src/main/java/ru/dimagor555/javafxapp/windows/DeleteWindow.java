package ru.dimagor555.javafxapp.windows;

import javafx.scene.Parent;
import ru.dimagor555.javafxapp.views.DeleteView;
import ru.dimagor555.presentation.DeletePresenter;

public class DeleteWindow extends Window {
    private final DeletePresenter presenter;
    private final DeleteView view;

    public DeleteWindow(DeletePresenter presenter) {
        super(WindowType.DELETE);
        this.presenter = presenter;

        Parent root = getStage().getScene().getRoot();
        view = new DeleteView(root, presenter);
    }

    public DeletePresenter getPresenter() {
        return presenter;
    }

    public DeleteView getView() {
        return view;
    }
}
