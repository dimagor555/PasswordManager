package ru.dimagor555.presentation;

public interface CreateUpdateView {
    void showSiteError();

    void hideSiteError();

    void showLoginError();

    void hideLoginError();

    void showPasswordError();

    void hidePasswordError();

    void showPassword(String password);

    String getSite();

    String getLogin();

    String getPassword();

    int getPasswordLength();

    boolean isSpecialSymbolsSelected();

    void disableButtons();

    void enableButtons();
}
