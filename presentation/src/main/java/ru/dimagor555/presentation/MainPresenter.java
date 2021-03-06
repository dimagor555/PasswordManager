package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.usecase.DecryptPassword;
import ru.dimagor555.usecase.GetAllRecords;
import ru.dimagor555.usecase.PutInClipboard;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainPresenter {
    private final GetAllRecords getAllRecords;
    private final DecryptPassword decryptPassword;
    private final PutInClipboard putInClipboard;
    private final Navigator navigator;
    private View view;

    public MainPresenter(GetAllRecords getAllRecords,
                         DecryptPassword decryptPassword,
                         PutInClipboard putInClipboard, Navigator navigator) {
        this.getAllRecords = getAllRecords;
        this.decryptPassword = decryptPassword;
        this.putInClipboard = putInClipboard;
        this.navigator = navigator;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void update() {
        getAllRecords.execute(new GetAllRecords.Callback() {
            @Override
            public void onAllRecordsLoaded(List<Record> allRecords) {
                view.renderRecords(allRecords);
            }

            @Override
            public void onDatabaseError(String message) {
                navigator.showDatabaseErrorDialog(message);
            }
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

    public void copyPassword(String encryptedPassword) {
        decryptPassword.execute(encryptedPassword, putInClipboard::execute);
    }

    public void filterRecords(String value) {
        Pattern pattern = Pattern.compile(value, Pattern.CASE_INSENSITIVE + Pattern.LITERAL);

        class Filter {
            boolean contains(String value) {
                return pattern.matcher(value).find();
            }
        }

        getAllRecords.execute(new GetAllRecords.Callback() {
            @Override
            public void onAllRecordsLoaded(List<Record> allRecords) {
                Filter filter = new Filter();
                List<Record> filteredRecords = allRecords.stream()
                        .filter(record -> filter.contains(record.getSite())
                        || filter.contains(record.getLogin()))
                        .collect(Collectors.toList());
                view.renderRecords(filteredRecords);
            }

            @Override
            public void onDatabaseError(String message) {
                navigator.showDatabaseErrorDialog(message);
            }
        });
    }

    public void changeMasterPassword() {
        navigator.openMasterPasswordWindow(true);
    }

    public interface View {
        Record getSelected();

        void renderRecords(List<Record> records);
    }
}
