package com.khaled.jpa.learning.iheriting.non.entity.classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
        em = emf.createEntityManager();
    }

    @AfterAll
    static void destroyPersistenceContext() {
        em.close();
        emf.close();
    }
    @Test
    void create(){
        SomeEntity se = new SomeEntity(5);
        em.getTransaction().begin();
        em.persist(se);
        em.getTransaction().commit();
    }
}
