module dbdao {
    exports ru.dimagor555.dbdao;

    requires domain;
    requires repository;

    requires net.bytebuddy;

    requires com.fasterxml.classmate;
    requires com.sun.xml.bind;

    requires java.sql;
    requires java.xml.bind;
    requires java.persistence;
    requires java.naming;

    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    opens ru.dimagor555.dbdao;
}