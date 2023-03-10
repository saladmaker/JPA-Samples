package com.khaled.jpa.learning.embeddable.type.collection.map.askey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapAsKeyTest {

    private final String HOME = "HOME";
    private final String WORK = "WORK";
    private final String ALTERNATIVE = "ALTERNATIVE";

    private static final Adress HOME_PAUL
            = new Adress("lds", "arizona14", "Arizone");
    private static final Adress WORK_PAUL
            = new Adress("Arizona's court", "arizona14", "arizona");
    private static final Adress HOME_BOBBY
            = new Adress("GJHG", "453", "Chicago");
    private static final Adress WORK_BOBBY
            = new Adress("SDFLS", "234", "Iceland");
    private static final Adress ALTERNATIVE_PAUL
            = new Adress("ZXC", "kjk", "fds");
    private static final Customer PAUL
            = new Customer("Paul", "MPRPHY", "kah@gm.com");
    private static final Customer BOBBY
            = new Customer("BOBBY", "FISCHER", "BOBY@gm.com");

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupEmbeddableCollectionMapAsKey");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyEmbeddableCollectionMapAsKey");
        em = emf.createEntityManager();
    }

    @AfterEach
    void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void createCustomer() {
        em.getTransaction().begin();
        em.persist(PAUL);
        PAUL.addAdress(HOME_PAUL, HOME);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void createCustomerTest() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        var paulAdresses = paul.getAdresses();
        var expected = Map.of(HOME_PAUL, HOME);
        assertThat(paulAdresses, is(expected));
    }

    @Test
    @Order(3)
    void createBobbyAddAdresses() {
        em.getTransaction().begin();
        em.persist(BOBBY);
        BOBBY.addAdress(HOME_BOBBY, HOME);
        BOBBY.addAdress(WORK_BOBBY, WORK);
        em.getTransaction().commit();
    }

    @Test
    @Order(4)
    void createBobbyAddAdressesTest() {
        var bobby = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", BOBBY.getEmail())
                .getSingleResult();
        var bobbyAdresses = bobby.getAdresses();
        var expected = Map.of(HOME, HOME_BOBBY, WORK, WORK_BOBBY);
        assertThat(expected, is(expected));
    }

    @Test
    @Order(5)
    void addAdressMorphy() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        em.getTransaction().begin();
        paul.addAdress(WORK_PAUL, WORK);
        em.getTransaction().commit();
    }

    @Test
    @Order(6)
    void addAdressMorphyTest() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        var paulAdresses = paul.getAdresses();
        var expected = Map.of(WORK_PAUL, WORK, HOME_PAUL, HOME);
        assertThat(paulAdresses, is(expected));
    }

    @Test
    @Order(7)
    void removeAddAdressMorphy() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        em.getTransaction().begin();
        paul.removeAdress(HOME_PAUL);
        paul.addAdress(ALTERNATIVE_PAUL, ALTERNATIVE);
        em.getTransaction().commit();
    }

    @Test
    @Order(8)
    void removeAddAdressMorphyTest() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        var paulAdresses = paul.getAdresses();
        Map<Adress, String> expected = Map.of(ALTERNATIVE_PAUL, ALTERNATIVE, WORK_PAUL, WORK);
        assertThat(paulAdresses, is(expected));
    }

    @Test
    @Order(9)
    void duplicateAdressMorphy() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        em.getTransaction().begin();
        paul.addAdress(ALTERNATIVE_PAUL, ALTERNATIVE);
        em.getTransaction().commit();
        var paulAdresses = paul.getAdresses();
        assertThat(paulAdresses.size(), is(2));
    }
}
