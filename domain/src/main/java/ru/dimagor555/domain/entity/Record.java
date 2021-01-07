package ru.dimagor555.domain.entity;

public class Record {
    private String site;
    private String login;
    private String password;

    public Record(String site, String login, String password) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record that = (Record) o;

        if (!site.equals(that.site)) return false;
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        int result = site.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }
}
