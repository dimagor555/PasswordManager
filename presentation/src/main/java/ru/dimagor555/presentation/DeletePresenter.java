package ru.dimagor555.presentation;

import ru.dimagor555.domain.entity.Record;
import ru.dimagor555.usecase.DeleteRecord;

public class DeletePresenter {
    private final DeleteRecord deleteRecord;
    private final Navigator navigator;
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
        currentRecord = toDelete;
        view.setText("Are you sure you want to delete record for\n" +
                "site: " + toDelete.getSite() + "\n" +
                "login: " + toDelete.getLogin());
        view.setFocusOnCancelBtn();
    }

    public void acceptDelete() {
        deleteRecord.execute(currentRecord, new DeleteRecord.Callback() {
            @Override
            public void onRecordDeleted() {
                navigator.closeDeleteWindow();
                navigator.updateMainWindow();
            }
        });
    }

    public void cancelDelete() {
        navigator.closeDeleteWindow();
    }

    public interface View {
        void setText(String text);
        
        void setFocusOnCancelBtn();
    }
}
