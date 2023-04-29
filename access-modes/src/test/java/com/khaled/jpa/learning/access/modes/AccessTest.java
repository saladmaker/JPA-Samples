package com.khaled.jpa.learning.access.modes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccessTest {
    
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("Access-Mode");

    private EntityManager entityManager;


    /**
     * tearing down the database after all test by closing the entityManagerFactory
     */
    @AfterAll
    static void tearDown(){
        entityManagerFactory.close();
    }

    @BeforeEach
    void createContext() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    
    @AfterEach
    void clearContext() {
        entityManager.close();
        
    }
    
    @Test
    void customerTest() {
        Customer customer = new Customer("c1", "c2",  LocalDate.of(2055, 11, 26));
        entityManager.getTransaction().begin();

        entityManager.persist(customer);
        entityManager.getTransaction().commit();

        var actualId = customer.getId();
        assertNotNull(customer.getId());

        var actualCustomer = entityManager.find(Customer.class, actualId);
        assertEquals(customer, actualCustomer);
        assertEquals(customer.getFirstName(), actualCustomer.getFirstName());
        assertEquals(customer.getLastName(), actualCustomer.getLastName());
        assertEquals(customer.getDateOfBirth(), actualCustomer.getDateOfBirth());
    }


    @Test
    void empolye() {
        Employe employe = new Employe("mohamed",
                "assem",
                new BigDecimal("25010.68"));
        entityManager.getTransaction().begin();

        entityManager.persist(employe);
        entityManager.getTransaction().commit();

        var expectedId = employe.getId();
        assertNotNull(expectedId);

        var actualEmploye = entityManager.find(Employe.class, expectedId);
        assertEquals(employe, actualEmploye);
        assertEquals(employe.getFirstName(), actualEmploye.getFirstName());
        assertEquals(employe.getLastName(), actualEmploye.getLastName());
        assertEquals(employe.getWage(), actualEmploye.getWage());
    }

    @Test
    void supplierTest() {
        final String localPhoneNumber = "0541412546";
        final String expectedCannonicalPhoneNumber = "+213541412546";
        Supplier supplier = new Supplier("khaled", localPhoneNumber);
        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.getTransaction().commit();

        var expectedId = supplier.getId();
        assertNotNull(expectedId);

        var actualSupplier = entityManager.find(Supplier.class, expectedId);
        assertEquals(supplier, actualSupplier);
        assertEquals(supplier.getName(), actualSupplier.getName());
        assertEquals(supplier.getPhoneNumber(), actualSupplier.getPhoneNumber());
        assertEquals(expectedCannonicalPhoneNumber, actualSupplier.getCannonicalPhoneNumber());

    }

}
