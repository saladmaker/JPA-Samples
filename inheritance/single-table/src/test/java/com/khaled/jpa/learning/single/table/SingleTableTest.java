package com.khaled.jpa.learning.single.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SingleTableTest {

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
    void createEmployees() {
        FullTimeEmployee fe = new FullTimeEmployee(BigDecimal.ZERO, "sfkhsk",
                "sfkhsdf", "skdfhsdk");
        PartTimeEmployee pe = new PartTimeEmployee(BigDecimal.ONE, "kdfhsk",
                "sdf;ls", "sdfls");
        em.getTransaction().begin();
        em.persist(fe);
        em.persist(pe);
        em.getTransaction().commit();

    }

    @Test
    @Order(2)
    void createEmployeesTest() {
        var fe = em.createQuery("SELECT f FROM FullTimeEmployee f", FullTimeEmployee.class)
                .getSingleResult();
        assertThat(fe.getId(), notNullValue());

        var pe = em.createQuery("SELECT p FROM PartTimeEmployee p", PartTimeEmployee.class)
                .getSingleResult();
        assertThat(pe.getId(), notNullValue());

        var ae = em.createQuery("SELECT v FROM Employee v", Employee.class)
                .getResultList();
        assertThat(ae, hasSize(2));
    }

}
