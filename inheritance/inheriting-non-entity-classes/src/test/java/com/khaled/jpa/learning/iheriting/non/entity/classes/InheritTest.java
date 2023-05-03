package com.khaled.jpa.learning.iheriting.non.entity.classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InheritTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("PU");

    }

    @BeforeEach
    public void createContext() {
        em = emf.createEntityManager();
    }

    @AfterEach
    public void clearContext() {
        em.close();
    }

    @AfterAll
    static void destroyPersistenceContext() {
        emf.close();
    }

    @Test
    void persist() {
        SomeEntity se = new SomeEntity(5, 10);
        assertThat(se.transientCalculation(), is(50));

        em.getTransaction().begin();
        em.persist(se);
        em.getTransaction().commit();
    }

    @Test
    void test() {
        var se = em.createQuery("SELECT E FROM SomeEntity E", SomeEntity.class)
                .getSingleResult();

        assertThat(se.transientCalculation(), is(0));

    }
}
