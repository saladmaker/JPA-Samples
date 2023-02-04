package com.khaled.jpa.learning.table.per.clas;

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
 * @author khale
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TablPerClassTest {

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
    void createCars() {
        ElectricCar ec
                = new ElectricCar(200, 9,
                        "3123jl231l23", "tesla", "420", 100);
        DieselCar dc = new DieselCar(300, 8,
                "dslfjl34l", "porch", "911", 300);
        em.getTransaction().begin();
        em.persist(ec);
        em.persist(dc);
        em.getTransaction().commit();

    }

    @Test
    @Order(2)
    void createCarsTest() {
        var ec = em.createQuery(
                "SELECT e FROM ElectricCar e",
                ElectricCar.class
        ).getSingleResult();
        assertThat(ec, notNullValue());
        assertThat(ec.getId(), notNullValue());
        var dc = em.createQuery(
                "SELECT d FROM DieselCar d",
                DieselCar.class
        ).getSingleResult();
        assertThat(dc, notNullValue());
        assertThat(dc.getId(), notNullValue());
        var cs = em.createQuery(
                "SELECT v FROM Vehicle v",
                Vehicle.class
        ).getResultList();
        assertThat(cs, hasSize(2));
        cs.forEach(System.out::println);
    }

}
