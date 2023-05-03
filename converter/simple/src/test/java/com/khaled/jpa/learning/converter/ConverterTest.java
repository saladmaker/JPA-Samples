package com.khaled.jpa.learning.converter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.MonthDay;
/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConverterTest {

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
        HollyDay tree = new HollyDay("The Tree Hollyday", MonthDay.of(3,14));
        em.getTransaction().begin();
        em.persist(tree);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void retrieveTest() {
        em.getTransaction().begin();
        em.createQuery("SELECT h FROM HollyDay h", HollyDay.class)
                .getSingleResult();
        em.getTransaction().commit();
    }
}
