package ru.dimagor555.presentation;

public class MainPresenter {
    private final Navigator navigator;
    private View view;

    public MainPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setView(View view) { this.view = view; }

    public void updateSelectedRecord() {
        navigator.openUpdateWindow();
    }

    public void createSelectedRecord() {
        navigator.openCreateWindow();
    }

    public void deleteSelectedRecord() {
        navigator.openDeleteWindow();
    }

    public interface View {

    }
}
