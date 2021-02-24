package ru.dimagor555.javafxapp.windows;

import java.util.Optional;

public class CssLoader {
    private static final String COMMON_CSS = "common";

    public Optional<String> getCssPath(String fileName) {
        var cssPath = "/css/" + fileName + ".css";
        var resource = getClass().getResource(cssPath);
        return resource != null
                ? Optional.of(resource.toExternalForm())
                : Optional.empty();
    }

    public String getCommonCssPath() {
        return getCssPath(COMMON_CSS).get();
    }
}
