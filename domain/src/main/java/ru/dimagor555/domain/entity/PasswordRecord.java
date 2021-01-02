package ru.dimagor555.domain.entity;

public class PasswordRecord {
    private String site;
    private String login;
    private String password;

    public PasswordRecord(String site, String login, String password) {
        this.site = site;
        this.login = login;
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
