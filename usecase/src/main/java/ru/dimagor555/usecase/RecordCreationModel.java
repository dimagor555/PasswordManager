package ru.dimagor555.usecase;

public final class RecordCreationModel {
    private final String site;
    private final String login;
    private final String password;

    public RecordCreationModel(String site, String login, String password) {
        this.site = site;
        this.login = login;
        this.password = password;
    }

    public String site() {
        return site;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }
}
