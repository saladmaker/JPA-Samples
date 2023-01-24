package com.khaled.jpa.learning.one.to.one.unidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OneToOneUnidirectionalTest {

    private final static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("UOneToOne");
    private EntityManager entityManager;

    /**
     * tearing down the database after all test by closing the
     * entityManagerFactory
     */
    @AfterAll
    static void tearDown() {
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
    void persistDeskTest() {
        Desk department = new Desk("1/3");
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(2)
    void persistEmployeeTest() {
        Employee employee = new Employee("kada");
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(3)
    void unidirectionalMappingTest() {
        Employee ourEmployee = entityManager
                .createQuery("SELECT e FROM Employee e",
                        Employee.class)
                .getSingleResult();
        Desk ourDesk = entityManager
                .createQuery("SELECT d FROM Desk d",
                        Desk.class)
                .getSingleResult();
        entityManager.getTransaction().begin();
        ourEmployee.setDesk(ourDesk);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(4)
    void retrieveTest() {
        Employee ourEmployee = entityManager
                .createQuery("SELECT e FROM Employee e",
                        Employee.class)
                .getSingleResult();
        String floor = ourEmployee.getDesk().getFloor();
        assertEquals("1/3", floor);
    }

    @Test
    @Order(5)
    void removeRelationshipTest() {
        entityManager.getTransaction().begin();
        entityManager
                .createQuery("SELECT e FROM Employee e",
                        Employee.class)
                .getSingleResult()
                .setDesk(null);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(6)
    void addAnotherEmployeeTest() {
        Employee employee = new Employee("mohamed");
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(7)
    void contradictionMappingTest() {
        var emmployees = entityManager
                .createQuery("SELECT e FROM Employee e",
                        Employee.class)
                .getResultList();
        Desk ourDesk = entityManager
                .createQuery("SELECT d FROM Desk d",
                        Desk.class)
                .getSingleResult();
        PersistenceException assertThrows
                = Assertions.assertThrows(PersistenceException.class,
                        () -> {
                            entityManager.getTransaction().begin();
                            emmployees.stream().
                                    forEach(e -> e.setDesk(ourDesk));
                            entityManager.getTransaction().commit();
                        });
    }
}
