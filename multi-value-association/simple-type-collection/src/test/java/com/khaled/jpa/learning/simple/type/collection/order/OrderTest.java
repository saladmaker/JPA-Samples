package com.khaled.jpa.learning.simple.type.collection.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
public class OrderTest {

    private final String JAVA = "Java";
    private final String JAKARTAEE = "Jakarta EE";
    private final String MICROPROFILE = "MicroProfile";
    private final String HELIDON = "Helidon";

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupUCollectionElementOrder");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyUCollectionElementOrder");
        em = emf.createEntityManager();
    }

    @AfterEach
    void destroyPersistenceContext() {
        em.close();
        emf.close();

    }

    @Test
    @Order(1)
    void createUserIntrest() {
        var user = new Users("dsdfsf", "dsfsd");
        user.addIntrest(JAVA);
        user.addIntrest(JAKARTAEE);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void createUserIntrestTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        assertEquals(
                List.of(JAVA, JAKARTAEE),
                actualIntrest
        );
        assertNotEquals(
                List.of(JAKARTAEE, JAVA),
                actualIntrest
        );

    }

    @Test
    @Order(3)
    void removeIntrest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        em.getTransaction().begin();
        actualIntrest.removeIf(JAKARTAEE::equals);
        em.getTransaction().commit();
    }

    @Test
    @Order(4)
    void removeIntrestTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        assertEquals(actualIntrest, List.of(JAVA));
    }

    @Test
    @Order(5)
    void insertAtTheStart() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        em.getTransaction().begin();
        actualIntrest.add(0, HELIDON);
        em.getTransaction().commit();
    }

    @Test
    @Order(6)
    void insertAtTheStartTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        assertEquals(List.of(HELIDON, JAVA), actualIntrest);
        assertNotEquals(List.of(JAVA, HELIDON), actualIntrest);
    }

    @Test
    @Order(7)
    void insertInTheMiddle() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        em.getTransaction().begin();
        actualIntrest.add(1, MICROPROFILE);
        em.getTransaction().commit();
    }
    @Test
    @Order(8)
    void insertInTheMiddleTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        assertEquals(List.of(
                HELIDON, MICROPROFILE, JAVA),
                actualIntrest
        );
        assertNotEquals(List.of(
                HELIDON, JAVA, MICROPROFILE),
                actualIntrest
        );
    }
}
