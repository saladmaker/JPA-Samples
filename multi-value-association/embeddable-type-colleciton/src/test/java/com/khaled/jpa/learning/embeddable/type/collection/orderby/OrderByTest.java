package com.khaled.jpa.learning.embeddable.type.collection.orderby;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
public class OrderByTest {

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
        emf = Persistence.createEntityManagerFactory("setupEmbeddableCollectionOrderBy");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyEmbeddableCollectionOrderBy");
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
        PAUL.addAdress(HOME_PAUL);
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
        var expected = List.of(HOME_PAUL);
        assertThat(paulAdresses, equalTo(expected));
    }

    @Test
    @Order(3)
    void createBobbyAddAdresses() {
        em.getTransaction().begin();
        em.persist(BOBBY);
        BOBBY.addAdress(HOME_BOBBY);
        BOBBY.addAdress(WORK_BOBBY);
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
        var expected = Arrays.asList(HOME_BOBBY, WORK_BOBBY);
        expected.sort(Comparator.naturalOrder());
        assertThat(expected, equalTo(bobbyAdresses));
        var reversed = expected; 
        reversed.sort(Comparator.<Adress>naturalOrder().reversed());
        assertThat(expected, not(bobbyAdresses));
    }

    @Test
    @Order(5)
    void addFirstAdress() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        em.getTransaction().begin();
        paul.getAdresses().add(0, WORK_PAUL);
        em.getTransaction().commit();
    }

    @Test
    @Order(6)
    void addFirstAdressTest() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        var paulAdresses = paul.getAdresses();
        var expected = Arrays.asList(WORK_PAUL, HOME_PAUL);
        expected.sort(Comparator.naturalOrder());
        assertThat(paulAdresses, equalTo(expected));
        var reversed = expected;
        reversed.sort(Comparator.<Adress>naturalOrder().reversed());
        assertThat(paulAdresses, not(reversed));
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
        paul.addAdress(ALTERNATIVE_PAUL);
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
        var expected = Arrays.asList(WORK_PAUL, ALTERNATIVE_PAUL);
        expected.sort(Comparator.naturalOrder());
        assertThat(paulAdresses, equalTo(expected));
        var reversed = expected;
        reversed.sort(Comparator.<Adress>naturalOrder().reversed());
        assertThat(paulAdresses, not(reversed));
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
        paul.addAdress(WORK_PAUL);
        em.getTransaction().commit();
        assertThat(paul.getAdresses().size(), is(3));
    }

    @Test
    @Order(10)
    void duplicateAdressMorphyTest() {
        var paul = em.createQuery(
                "SELECT c FROM Customer c WHERE c.email = :email",
                Customer.class
        ).setParameter("email", PAUL.getEmail())
                .getSingleResult();
        var paulAdresses = paul.getAdresses();
        var expected = Arrays.asList(WORK_PAUL, ALTERNATIVE_PAUL);
        expected.sort(Comparator.naturalOrder());
        assertThat(paulAdresses, is(expected));
        assertThat(paulAdresses, hasSize(2));
    }
}
