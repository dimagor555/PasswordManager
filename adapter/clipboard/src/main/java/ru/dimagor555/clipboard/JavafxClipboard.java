package ru.dimagor555.clipboard;

import javafx.scene.input.ClipboardContent;
import ru.dimagor555.domain.port.Clipboard;

public class JavafxClipboard implements Clipboard {
    @Override
    public void put(String text) {
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        javafx.scene.input.Clipboard.getSystemClipboard().setContent(content);
    }
}
