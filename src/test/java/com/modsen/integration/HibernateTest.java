package com.modsen.integration;

import com.modsen.SessionFactoryMaker;
import com.modsen.model.manytomany.Student;
import com.modsen.model.manytomany.Teacher;
import com.modsen.model.onetomany.Book;
import com.modsen.model.onetomany.Tag;
import com.modsen.util.ClasspathUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateTest extends BasePostgres {

    private static SessionFactory sessionFactory;
    private static ClasspathUtil classpathUtil;
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_NAME = "book_db";


    @BeforeAll
    static void setUpAll() {
        classpathUtil = new ClasspathUtil();
        Integer port = postgresContainer.getFirstMappedPort();
        sessionFactory = SessionFactoryMaker.setUp(buildUrl(port, DB_NAME), DB_USERNAME, DB_PASSWORD);
    }

    private static String buildUrl(Integer port, String databaseName) {
        return "jdbc:postgresql://localhost:" + port + "/" + databaseName;
    }

    @BeforeEach
    void setUp() {
        sessionFactory.inTransaction(session -> {
                    String deleteScript = classpathUtil.readFile("/sql/delete-tables.sql");
                    String ddlScript = classpathUtil.readFile("/sql/ddl.sql");
                    String dmlScript = classpathUtil.readFile("/sql/dml.sql");

                    session.createNativeMutationQuery(deleteScript).executeUpdate();
                    session.createNativeMutationQuery(ddlScript).executeUpdate();
                    session.createNativeMutationQuery(dmlScript).executeUpdate();
                }
        );
    }

    @Test
    void check_sessionFactory_shouldBeNotNull() {
        assertThat(sessionFactory).isNotNull();
    }

    @Test
    public void check_persist_shouldSaveEntity() {
        String name = "new name";
        String ISBN = "ISBNunique";
        sessionFactory.inTransaction(session -> {
            Book book = new Book(
                    null, name, ISBN,
                    List.of(new Tag(2, null))
            );
            session.persist(book);
        });

        try (Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, 3);

            assertThat(book).isNotNull();
            assertThat(book.getName()).isEqualTo(name);
            assertThat(book.getISBN()).isEqualTo(ISBN);
        }
    }

    @Test
    public void check_persist_entityShouldBeInPersistentState() {
        Book book = new Book(null, "name", "new isbn1234", List.of(new Tag(1, null)));

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();

            assertThat(book.getId()).isEqualTo(3);
            assertThat(session.contains(book)).isTrue();
            assertThat(session.contains(book.getTags().get(0))).isFalse(); // nested entities are not in context after persist

        }
    }

    @Test
    public void check_getById_entityShouldBeInPersistentState() {
        try (Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, 1);

            assertThat(session.contains(book)).isTrue();
        }
    }

    @Test
    public void check_remove_entityShouldBeInRemovedState() {
        try (Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, 1);

            session.remove(book);

            assertThat(session.contains(book)).isFalse();
        }
    }

    @Test
    public void check_merge_shouldReturnEntityFromPersistentContextWithUpdatedName() {
        try (Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, 1);

            session.evict(book);

            assertThat(session.contains(book)).isFalse();

            String newName = "Updated name";
            book.setName(newName);
            Book mergedBook = session.merge(book);

            assertThat(session.contains(book)).isFalse(); // hibernate doesn't add passed object to persistent context
            assertThat(session.contains(mergedBook)).isTrue();
            assertThat(mergedBook.getName()).isEqualTo(newName);
        }
    }

    // lazy generates 2 queries
    // eager generates 1 query
    @Test
    public void check_oneToMany_fetchTypes() {
        try (Session session = sessionFactory.openSession()) {
            Book book = session.get(Book.class, 1);

            assertThat(book).isNotNull();
            assertThat(book.getTags()).isNotNull();
            assertThat(book.getTags()).hasSize(4);
            for(var tag : book.getTags()) {
                System.out.println(tag);
            }
        }
    }

    @Test
    public void check_manyToMany_fetchTypes() {
//        try (Session session = sessionFactory.openSession()) {
//            Teacher teacher = session.get(Teacher.class, 1);
//            assertThat(teacher).isNotNull();
//            assertThat(teacher.getStudents()).hasSize(4);
//            for(var s : teacher.getStudents()) {
//                System.out.println(s);
//            }
//
//            Student student = session.get(Student.class, 4);
//            assertThat(student).isNotNull();
//            assertThat(student.getTeachers()).hasSize(3);
//            for(var t : student.getTeachers()) {
//                System.out.println(t);
//            }
//        }
    }
}
