package com.khaled.jpa.learning.basic.mapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class ModificationTest {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BasicMapping");;
    private EntityManager entityManager;
    final static String EXPECTED_LAST_NAME = "B";
    static long expectedId;
    
    
    /**
     * tearing down the database after each test by closing the entityManagerFactory!
     * Note this only works with h2 in memory database.
     * this will not work also if you Junit is configured to run tests in parallel mode although I didn't test it.
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
    void create() {        
        var customer = new Customer("f2", "l2",
                        LocalDate.of(1994, 11, 26));

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        expectedId = customer.getId();
        
    }
    @Test
    @Order(2)
    void update() {
        
        Customer actualCustomer = entityManager.find(Customer.class, expectedId);  
        //changing the last name in a transaction scope
        entityManager.getTransaction().begin();
        actualCustomer.setLastName(EXPECTED_LAST_NAME);
        entityManager.getTransaction().commit();

        
    }
    @Test
    @Order(2)
    void test() {
        //select query doesn't require transaction
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c",
                Customer.class);
        Customer actualCustomer = query.getSingleResult();
        assertEquals(expectedId, actualCustomer.getId());
        assertEquals(EXPECTED_LAST_NAME, actualCustomer.getLastName());
    }    
}
