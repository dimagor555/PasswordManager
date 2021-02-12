package ru.dimagor555.dbdao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RECORDS")
public class RecordModel {
    @Id
    private long id;
    private String site;
    private String login;
    @Column(length = 512)
    private String pass;

    public RecordModel() {
    }

    public RecordModel(String site, String login, String pass) {
        this.site = site;
        this.login = login;
        this.pass = pass;
    }

    public RecordModel(long id, String site, String login, String pass) {
        this.id = id;
        this.site = site;
        this.login = login;
        this.pass = pass;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
