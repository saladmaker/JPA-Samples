package com.khaled.jpa.learning.many.to.many.bidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManyToManyBidirectionalTest {

    private static final Author DOSTOEVSKY
            = new Author("Jan", "Beernink", "");
    private static final Author ERNEST_HEMINGWAR
            = new Author("Ernest", "Hemingway", "");
    private static final Author ARJAN_TIJMS
            = new Author("Arjan", "Tijmes", "");
    private static final Book PRO_CDI
            = new Book("54454545454", "Pro CDI", 200);
    private static final Book A_FAREWELL_TO_THE_ARMS
            = new Book("525454", "A Farewell To The Arms", 500);

    private static final Book JSF
            = new Book("354535", "jsf", 500);
    private static final Book OLD_MAN_AND_THE_SEA
            = new Book("354535", "The Old Man And The Sea", 500);

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupUManyToManyBidirectional");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyUManyToManyBidirectional");
        em = emf.createEntityManager();
    }

    @AfterEach
    void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void creation() {
        em.getTransaction().begin();
        em.persist(ERNEST_HEMINGWAR);
        em.persist(ARJAN_TIJMS);
        em.persist(OLD_MAN_AND_THE_SEA);
        ERNEST_HEMINGWAR.addBook(OLD_MAN_AND_THE_SEA);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void creationTest() {
        var ernest = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ERNEST_HEMINGWAR.getId())
                .getSingleResult();
        var actualBooks = ernest.getBooks();
        assertEquals(Set.of(OLD_MAN_AND_THE_SEA), actualBooks);
    }

    @Test
    @Order(3)
    void addBooks() {
        var ernest = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ERNEST_HEMINGWAR.getId())
                .getSingleResult();
        var arjan = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ARJAN_TIJMS.getId())
                .getSingleResult();
        em.getTransaction().begin();
        ernest.addBook(A_FAREWELL_TO_THE_ARMS);
        arjan.addBook(JSF);
        em.getTransaction().commit();
    }

    @Test
    @Order(4)
    void addBooksTest() {
        var ernest = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ERNEST_HEMINGWAR.getId())
                .getSingleResult();
        var arjan = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ARJAN_TIJMS.getId())
                .getSingleResult();
        var ernestBooks = ernest.getBooks();
        assertEquals(
                Set.of(A_FAREWELL_TO_THE_ARMS, OLD_MAN_AND_THE_SEA),
                ernestBooks
        );
        var arjanBooks = arjan.getBooks();
        assertEquals(Set.of(JSF), arjanBooks);
        
        System.out.println(Path.of("").toAbsolutePath());
    }
    @Test
    @Order(5)
    void removeBook(){
        var ernest = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ERNEST_HEMINGWAR.getId())
                .getSingleResult();
        var oldMan = em.createQuery(
                "SELECT b FROM Book b WHERE b.id = :id",
                Book.class
        ).setParameter("id", OLD_MAN_AND_THE_SEA.getId())
                .getSingleResult();
        em.getTransaction().begin();
        ernest.removeBook(oldMan);
        em.getTransaction().commit();
    }
    @Test
    @Order(6)
    void removeBookTest(){
        var ernest = em.createQuery(
                "SELECT a FROM Author a WHERE a.id = :id",
                Author.class
        ).setParameter("id", ERNEST_HEMINGWAR.getId())
                .getSingleResult();
        var ernestBooks = ernest.getBooks();
        assertEquals(Set.of(A_FAREWELL_TO_THE_ARMS), ernestBooks);
    }
}
