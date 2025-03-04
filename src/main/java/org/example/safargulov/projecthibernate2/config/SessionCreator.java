package org.example.safargulov.projecthibernate2.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;

    public SessionCreator() {
        Configuration configuration =new Configuration();
        this.sessionFactory = configuration.configure().buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
