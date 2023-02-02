package com.khaled.jpa.learning.simple.type.collection.list;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListTest {

    private final String JAVA = "Java";
    private final String JAKARTAEE = "Jakarta EE";
    private final String MICROPROFILE = "MicroProfile";
    private final String HELIDON = "Helidon";

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupUCollectionElementList");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyUCollectionElementList");
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
        assertTrue(actualIntrest.containsAll(List.of(JAVA, JAKARTAEE)));
        assertTrue(List.of(JAVA, JAKARTAEE).containsAll(actualIntrest));
    }

    @Test
    @Order(3)
    void removeIntrest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        em.getTransaction().begin();
        user.getIntrests().removeIf(JAKARTAEE::equals);
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
        assertTrue(actualIntrest.containsAll(List.of(JAVA)));
        assertTrue(List.of(JAVA).containsAll(actualIntrest));
    }

    @Test
    @Order(5)
    void addIntrest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        em.getTransaction().begin();
        var intrests = user.getIntrests();
        intrests.add(MICROPROFILE);
        intrests.add(HELIDON);
        em.getTransaction().commit();
    }

    @Test
    @Order(6)
    void addIntrestTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        var actualIntrest = user.getIntrests();
        assertTrue(actualIntrest.containsAll(List.of(JAVA, MICROPROFILE, HELIDON)));
        assertTrue(List.of(JAVA, HELIDON, MICROPROFILE).containsAll(actualIntrest));
    }

    @Test
    @Order(7)
    void addDuplicateTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        em.getTransaction().begin();
        var intrests = user.getIntrests();
        intrests.add(MICROPROFILE);
        intrests.add(HELIDON);
        em.getTransaction().commit();
    }

}
