package ru.dimagor555.dbdao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionFactoryUtil {
    private static String DB_PATH = System.getProperty("user.home") + "/.PasswordManager/data/db";
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .applySetting("hibernate.connection.url", "jdbc:h2:file:" + DB_PATH)
                    .build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata()
                        .buildSessionFactory();
            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }

    public static Session openSession() {
        return getSessionFactory().openSession();
    }
}
