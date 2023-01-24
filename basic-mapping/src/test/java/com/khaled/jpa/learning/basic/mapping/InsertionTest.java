package com.khaled.jpa.learning.basic.mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class InsertionTest {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BasicMapping");;
    private EntityManager entityManager;
    static Customer expectedCustomer;
    
    
    /**
     * tearing down the database after all test ran by closing the entityManagerFactory!
     */
    @AfterAll
    static void treaDown(){
        entityManagerFactory.close();
    }
    
    @BeforeEach
    void createContext() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    
    @AfterEach
    void clearContext() {
        this.entityManager.close();
    }
    
    @Test
    @Order(1)
    void identityTest() {
        expectedCustomer = new Customer("f1",
                        "l1",
                        LocalDate.of(1994, 11, 26));
        entityManager.getTransaction().begin();
        entityManager.persist(expectedCustomer);
        entityManager.getTransaction().commit();

        Long actualId = expectedCustomer.getId();
        assertNotNull(actualId);
    }
    @Test
    @Order(2)
    void Insertiontest() {
        Customer actualCustomer = entityManager.find(Customer.class, expectedCustomer.getId());
        assertEquals(expectedCustomer, actualCustomer);
        assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
        assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
        assertEquals(expectedCustomer.getDateOfBirth(), actualCustomer.getDateOfBirth());

    }
}
