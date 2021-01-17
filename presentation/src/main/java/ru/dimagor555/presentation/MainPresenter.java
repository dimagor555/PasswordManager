package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.usecase.GetAllRecords;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainPresenter {
    private final GetAllRecords getAllRecords;
    private final Navigator navigator;
    private View view;

    public MainPresenter(GetAllRecords getAllRecords, Navigator navigator) {
        this.getAllRecords = getAllRecords;
        this.navigator = navigator;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void update() {
        getAllRecords.execute(allRecords -> {
            List<Record> records = new ArrayList<>(allRecords);
            records.sort(Comparator.comparing(Record::getSite)
                    .thenComparing(Record::getLogin));
            view.renderRecords(records);
        });
    }

    public void updateSelectedRecord() {
        Record selected = view.getSelected();
        if (selected != null) {
            navigator.openUpdateWindow(selected);
        }
    }

    public void createRecord() {
        navigator.openCreateWindow();
    }

    public void deleteSelectedRecord() {
        Record selected = view.getSelected();
        if (selected != null) {
            navigator.openDeleteWindow(selected);
        }
    }

    public interface View {
        Record getSelected();

        void renderRecords(List<Record> records);
    }
}
