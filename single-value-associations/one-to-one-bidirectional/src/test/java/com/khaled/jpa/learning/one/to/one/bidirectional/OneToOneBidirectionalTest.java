package com.khaled.jpa.learning.one.to.one.bidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
public class OneToOneBidirectionalTest {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("setupBOneToOne");;
    private static EntityManager entityManager;
    
    @BeforeAll
    static void generatingDatabase(){
        entityManagerFactory
                = Persistence.createEntityManagerFactory("setupBOneToOne");
        entityManager = entityManagerFactory.createEntityManager();
    }
    /**
     * tearing down the database after all test by closing the
     * entityManagerFactory
     */
    @BeforeEach
    void createContext() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("readyBOneToOne");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void clearContext() {
        this.entityManager.close();
        this.entityManagerFactory.close();
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
        Employee kada = new Employee("kada");
        Employee khaled = new Employee("khaled");
        entityManager.getTransaction().begin();
        entityManager.persist(kada);
        entityManager.persist(khaled);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(3)
    void unidirectionalMappingTest() {
        Employee firstEmployee = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.fullName=:name",
                        Employee.class)
                .setParameter("name", "khaled")
                .getResultList().get(0);
        Desk ourDesk = entityManager
                .createQuery("SELECT d FROM Desk d",
                        Desk.class)
                .getSingleResult();
        entityManager.getTransaction().begin();
        firstEmployee.setDesk(ourDesk);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(4)
    void inverseOwnerAccessTest() {
        Desk ourDesk = entityManager
                .createQuery("SELECT d FROM Desk d",
                        Desk.class)
                .getSingleResult();

        Employee employee = ourDesk.getEmployee();
        assertNotNull(employee);
    }

    @Test()
    @Order(5)
    void hackTheDeskTest() {
        Employee ourEmployee = entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.fullName=:name",
                        Employee.class)
                .setParameter("name", "kada")
                .getResultList().get(0);
        Desk ourDesk = entityManager
                .createQuery("SELECT d FROM Desk d",
                        Desk.class)
                .getSingleResult();
        assertThrows(PersistenceException.class, () -> {
            entityManager.getTransaction().begin();
            ourEmployee.setDesk(ourDesk);
            entityManager.getTransaction().commit();
        });
    }

}
