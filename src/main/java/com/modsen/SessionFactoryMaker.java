package com.modsen;

import com.modsen.model.manytomany.Student;
import com.modsen.model.manytomany.Teacher;
import com.modsen.model.onetomany.Book;
import com.modsen.model.onetomany.Tag;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryMaker {

    private static SessionFactory sessionFactory;

    private SessionFactoryMaker() {
    }

    public static SessionFactory setUp(String url, String username, String password) {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Tag.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Teacher.class)
                    .setProperty("hibernate.connection.url", url)
                    .setProperty("hibernate.connection.username", username)
                    .setProperty("hibernate.connection.password", password)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
