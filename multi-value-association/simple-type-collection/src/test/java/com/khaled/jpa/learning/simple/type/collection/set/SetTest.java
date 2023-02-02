package com.khaled.jpa.learning.simple.type.collection.set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SetTest {

    private final String JAVA = "Java";
    private final String JAKARTAEE = "Jakarta EE";
    private final String MICROPROFILE = "MicroProfile";
    private final String HELIDON = "Helidon";

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupUCollectionElementSet");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyUCollectionElementSet");
        em = emf.createEntityManager();
    }

    @AfterEach
    void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void createUser() {
        var user = new Users("khaled@ap.com", "adfs");
        user.addIntrest(JAVA);
        em.getTransaction().begin();
        em.persist(user);
        user.addIntrest(JAKARTAEE);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void createUserTest() {
        var actual = em.createQuery(
                "SELECT u FROM Users u",
                Users.class)
                .getSingleResult();
        assertNotNull(actual.getId());
        System.out.println("*****lazy loading here******");
        var intrest = actual.getIntrests();
        assertEquals(intrest, Set.of(JAVA, JAKARTAEE));
    }

    @Test
    @Order(3)
    void removeIntrest() {
        var actual = em.createQuery(
                "SELECT u FROM Users u",
                Users.class)
                .getSingleResult();
        var intrests = actual.getIntrests();
        em.getTransaction().begin();
        intrests.removeIf(JAVA::equals);
        em.getTransaction().commit();
    }

    @Test
    @Order(4)
    void removeIntrestTest() {
        var actual = em.createQuery(
                "SELECT u FROM Users u",
                Users.class)
                .getSingleResult();
        var intrest = actual.getIntrests();
        assertEquals(intrest, Set.of(JAKARTAEE));
    }

    @Test
    @Order(5)
    void addIntrests() {
        var insterst = Set.of(MICROPROFILE, HELIDON);
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class)
                .getSingleResult();
        em.getTransaction().begin();
        user.getIntrests().addAll(insterst);
        em.getTransaction().commit();
    }

    @Test
    @Order(6)
    void addIntrestsTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class)
                .getSingleResult();
        var actualIntrest = user.getIntrests();
        assertEquals(
                actualIntrest,
                Set.of(JAKARTAEE, HELIDON, MICROPROFILE)
        );
    }

    @Test
    @Order(7)
    void addDuplicateIntrestTest() {
        var user = em.createQuery(
                "SELECT u FROM Users u",
                Users.class
        ).getSingleResult();
        
        em.getTransaction().begin();
        boolean added = user.addIntrest(HELIDON);
        em.getTransaction().commit();
        assertFalse(added);
    }
}
