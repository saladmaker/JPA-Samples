package com.khaled.jpa.learning.sequence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
public class SequenceTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    @AfterAll
    static void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void persistDepartmentTest() {
        Department department = new Department("management");
        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();
    }


    @Test
    @Order(2)
    void retrieveTest() {
        em.getTransaction().begin();
        em.createQuery("SELECT d FROM Department d",
                        Department.class)
                .getSingleResult();
        em.getTransaction().commit();
    }
}
