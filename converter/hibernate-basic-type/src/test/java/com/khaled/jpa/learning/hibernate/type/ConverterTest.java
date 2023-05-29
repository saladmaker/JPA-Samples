package com.khaled.jpa.learning.hibernate.type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Year;


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

        Product s23 = new Product("S23", Year.of(2023));
        em.getTransaction().begin();
        em.persist(s23);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void retrieveTest() {
        em.getTransaction().begin();
        var product = em.createQuery("SELECT p FROM Product p", Product.class)
                .getSingleResult();
                        
        assertThat(product.getInception(), is(Year.of(2023)));
        em.getTransaction().commit();
    }
}
