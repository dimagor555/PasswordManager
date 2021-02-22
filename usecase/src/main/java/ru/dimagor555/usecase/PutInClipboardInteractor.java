package ru.dimagor555.usecase;

import ru.dimagor555.domain.port.Clipboard;

public class PutInClipboardInteractor extends Interactor implements PutInClipboard {
    private final Clipboard clipboard;

    public PutInClipboardInteractor(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override
    public void execute(String text) {
        executeMain(() -> clipboard.put(text));
    }
}
