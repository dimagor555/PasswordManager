package ru.dimagor555.domain.entity;

public class Record {
    private final long id;
    private String site;
    private String login;
    private String password;

    public Record(long id, String site, String login, String password) {
        this.id = id;
        this.site = site;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
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

    public boolean equalsBySiteAndLogin(Record that) {
        return this.site.equals(that.site) && this.login.equals(that.login);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        return id == record.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
