package com.khaled.jpa.learning.basic.mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
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
public class BasicMappingTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    void setup() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("BasicMapping");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    /**
     * tearing down the database after each test by closing the entityManagerFactory!
     * Note this only works with h2 in memory database.
     * this will not work also if you Junit is configured to run tests in parallel mode although I didn't test it.*/
    @AfterEach
    void tearDown() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    @Test
    @Order(1)
    void testInsertionAndIdentity() {
        Customer customer
                = new Customer("f1",
                        "l1",
                        LocalDate.of(1994, 11, 26));
        entityManager.getTransaction().begin();

        entityManager.persist(customer);
        entityManager.getTransaction().commit();

        Long actualId = customer.getId();
        assertNotNull(customer.getId());

        var actualCustomer = entityManager.find(Customer.class, actualId);
        assertEquals(actualCustomer, customer);
        assertEquals(actualCustomer.getFirstName(), customer.getFirstName());
        assertEquals(actualCustomer.getLastName(), customer.getLastName());
        assertEquals(actualCustomer.getDateOfBirth(), customer.getDateOfBirth());

    }

    @Test
    @Order(2)
    void update() {
        final var expectedLastName = "B";
        Customer customer
                = new Customer("f2", "l2",
                        LocalDate.of(1994, 11, 26));

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        //changing the last name in a transaction scope
        entityManager.getTransaction().begin();
        customer.setLastName(expectedLastName);
        entityManager.getTransaction().commit();

        //select query doesn't require transaction
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c",
                Customer.class);
        Customer actualCustomer = query.getSingleResult();
        assertEquals(expectedLastName, actualCustomer.getLastName());
    }

    @Test
    @Order(3)
    void deleteCustomerTest() throws InterruptedException {
        Thread.sleep(1000);
        Customer customer
                = new Customer("f3", "l3",
                        LocalDate.of(1995, 11, 26));

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        //removing the customer
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();

        //select query doesn't require 1 transaction
        TypedQuery<Customer> query = entityManager.createQuery("SELECT c FROM Customer c",
                Customer.class);
        var customers = query.getResultList();
        customers.forEach((var c)->System.out.println("-*-*-*"+c));
        assertTrue(customers.isEmpty());
    }
}
