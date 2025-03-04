package org.example.safargulov.projecthibernate2;

import org.example.safargulov.projecthibernate2.config.DataBaseConfig;
import org.example.safargulov.projecthibernate2.config.SessionCreator;
import org.hibernate.Session;

public class Application {
    public static void main(String[] args) {
        new DataBaseConfig().init();
        SessionCreator creator = new SessionCreator();
        try (Session session = creator.getSession()) {
            System.out.println("Hibernate initialized successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
