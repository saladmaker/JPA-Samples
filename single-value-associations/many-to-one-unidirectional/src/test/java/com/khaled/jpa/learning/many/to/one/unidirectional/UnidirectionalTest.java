package com.khaled.jpa.learning.many.to.one.unidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
public class UnidirectionalTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    static void generate() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("setupUOneToMany");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @BeforeEach
    void createContext() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("readyUOneToMany");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void clearContext() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @Order(1)
    void persistDepartmentTest() {
        Department department = new Department("management");
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(2)
    void persistEmployeeTest() {
        Employee employee
                = new Employee("mohamed", "ali", null);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(3)
    void unidirectionalMappingTest() {
        var ourEmployee = entityManager
                .createQuery("SELECT e FROM Employee e",
                        Employee.class)
                .getSingleResult();
        var ourDepartment = entityManager
                .createQuery("SELECT d FROM Department d",
                        Department.class)
                .getSingleResult();
        entityManager.getTransaction().begin();
        ourEmployee.setDepartment(ourDepartment);
        entityManager.getTransaction().commit();
    }
}
