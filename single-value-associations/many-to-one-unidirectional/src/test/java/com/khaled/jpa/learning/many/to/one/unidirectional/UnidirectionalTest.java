package com.khaled.jpa.learning.many.to.one.unidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    static void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("setupUOneToMany");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    static void tearDown() {
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
    void unidirectionalMappingTest() {
        entityManager.getTransaction().begin();
        
        Employee employee = new Employee("mohamed", "ali", null);
        var ourDepartment = entityManager
                .createQuery("SELECT d FROM Department d",
                        Department.class)
                .getSingleResult();
        
        employee.setDepartment(ourDepartment);
        entityManager.persist(employee);

        entityManager.getTransaction().commit();
    }
}
