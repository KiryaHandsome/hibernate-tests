package com.modsen;

import com.modsen.model.onetomany.Book;
import com.modsen.model.onetomany.Tag;

import java.util.List;


public class Main {

    public static void main(String[] args) {
//        SessionFactory sessionFactory = SessionFactoryMaker.setUp();
//        sessionFactory.inTransaction(session -> {
//                    List<Tag> tags = getTags();
//                    Book book = getBook(tags);
//                    for (var tag : tags) {
//                        session.persist(tag);
//                    }
//                    session.persist(book);
//                }
//        );
//        Session
    }

    static Book getBook(List<Tag> tags) {
        String name = "Bookname";
        String ISBN = "112401-14124-124-124";
        return new Book(null, name, ISBN, tags);
    }

    static List<Tag> getTags() {
        return List.of(
                new Tag(null, "documentary"),
                new Tag(null, "phantasy"),
                new Tag(null, "rom-com")
        );
    }

}