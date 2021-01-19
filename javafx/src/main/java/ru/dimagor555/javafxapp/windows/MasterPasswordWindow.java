package ru.dimagor555.javafxapp.windows;

import ru.dimagor555.javafxapp.views.MasterPasswordView;
import ru.dimagor555.presentation.MasterPasswordPresenter;

public class MasterPasswordWindow extends Window {
    private final MasterPasswordPresenter presenter;
    private final MasterPasswordView view;

    public MasterPasswordWindow(MasterPasswordPresenter presenter) {
        super(WindowType.MASTER_PASSWORD);
        this.presenter = presenter;

        var root = getStage().getScene().getRoot();
        view = new MasterPasswordView(root, presenter);
    }

    public MasterPasswordPresenter getPresenter() {
        return presenter;
    }

    public MasterPasswordView getView() {
        return view;
    }
}
