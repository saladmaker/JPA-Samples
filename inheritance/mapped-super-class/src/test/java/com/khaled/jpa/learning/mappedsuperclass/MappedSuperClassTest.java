package com.khaled.jpa.learning.mappedsuperclass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
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
public class MappedSuperClassTest {

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
    @Order(1)
    void presist() {
        var user = new Users("", "".toCharArray());
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void query() {
        var user = em.createQuery("SELECT u FROM Users u", Users.class)
                .getSingleResult();
        assertThat(user.getId(), notNullValue());
    }

}
