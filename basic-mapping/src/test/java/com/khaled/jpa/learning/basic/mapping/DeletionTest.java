package com.khaled.jpa.learning.basic.mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class DeletionTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    static Customer expectedCustomer;

    @BeforeEach
    void setup() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("BasicMapping");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * tearing down the database after each test by closing the
     * entityManagerFactory! Note this only works with h2 in memory database.
     * this will not work also if you Junit is configured to run tests in
     * parallel mode although I didn't test it.
     */
    @AfterEach
    void closeThePersistenceContext() {
        this.entityManager.close();
    }

    @AfterAll
    static void treaDown() {
        entityManagerFactory.close();
    }

    @Test
    @Order(1)
    void create() {
        expectedCustomer= new Customer("f3", "l3",
                        LocalDate.of(1995, 11, 26));

        entityManager.getTransaction().begin();
        entityManager.persist(expectedCustomer);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(2)
    void delete() {
        //removing the expectedCustomer
        entityManager.getTransaction().begin();
        /*
        Customer managedCustomer = entityManager.merge(expectedCustomer);
        entityManager.remove(managedCustomer);
         */
        /*
        entityManager.createQuery("DELETE FROM Customer c WHERE c.id=:id")
        .setParameter("id", expectedCustomer.getId())
        .executeUpdate();
         */
        Customer actualCustomer = entityManager.find(Customer.class, expectedCustomer.getId());
        entityManager.remove(actualCustomer);
        entityManager.getTransaction().commit();

    }

    @Test
    @Order(3)
    void test() {
        //select query doesn't require a transaction
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c",
                Customer.class);
        var expectedCustomers = query.getResultList();
        assertTrue(expectedCustomers.isEmpty());
    }
}
