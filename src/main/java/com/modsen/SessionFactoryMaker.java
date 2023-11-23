package com.modsen;

import com.modsen.model.Book;
import com.modsen.model.Tag;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryMaker {

    private static SessionFactory sessionFactory;

    public static SessionFactory getFactory() {
        if (sessionFactory == null) {
            return new Configuration()
                    .addAnnotatedClass(Tag.class)
                    .addAnnotatedClass(Book.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

}
