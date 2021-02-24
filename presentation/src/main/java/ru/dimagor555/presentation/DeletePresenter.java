package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.usecase.DeleteRecord;

public class DeletePresenter {
    private final DeleteRecord deleteRecord;
    private final Navigator navigator;
    private boolean deleteQueried = false;
    private Record currentRecord;
    private View view;

    public DeletePresenter(DeleteRecord deleteRecord, Navigator navigator) {
        this.deleteRecord = deleteRecord;
        this.navigator = navigator;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void reset(Record toDelete) {
        enable();
        currentRecord = toDelete;
        view.setText("Are you sure you want to delete record for\n" +
                "site: " + toDelete.getSite() + "\n" +
                "login: " + toDelete.getLogin());
        view.setFocusOnCancelBtn();
    }

    public void acceptDelete() {
        if (deleteQueried) {
            return;
        }
        disable();
        deleteRecord.execute(currentRecord, new DeleteRecord.Callback() {
            @Override
            public void onRecordDeleted() {
                navigator.closeDeleteWindow();
                navigator.updateMainWindow();
            }

            @Override
            public void onDatabaseError(String message) {
                navigator.showDatabaseErrorDialog(message);
                enable();
            }
        });
    }

    public void cancelDelete() {
        navigator.closeDeleteWindow();
    }

    private void disable() {
        deleteQueried = true;
        view.disableButtons();
    }

    private void enable() {
        deleteQueried = false;
        view.enableButtons();
    }

    public interface View {
        void setText(String text);
        
        void setFocusOnCancelBtn();

        void disableButtons();

        void enableButtons();
    }
}
