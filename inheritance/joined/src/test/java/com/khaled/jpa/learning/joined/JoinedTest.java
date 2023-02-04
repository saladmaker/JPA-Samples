package com.khaled.jpa.learning.joined;

import com.khaled.jpa.learning.single.table.Employee;
import com.khaled.jpa.learning.single.table.FullTimeEmployee;
import com.khaled.jpa.learning.single.table.PartTimeEmployee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
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
public class JoinedTest {

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
        FullTimeEmployee fe
                = new FullTimeEmployee(BigDecimal.ZERO, "sfkhsk",
                        "sfkhsdf", "skdfhsdk");
        PartTimeEmployee pe
                = new PartTimeEmployee(BigDecimal.ONE, "kdfhsk",
                        "sdf;ls", "sdfls");
        em.getTransaction().begin();
        em.persist(fe);
        em.persist(pe);
        em.getTransaction().commit();

    }

    @Test
    @Order(2)
    void createCarsTest() {
        var ec = em.createQuery(
                "SELECT f FROM FullTimeEmployee f",
                FullTimeEmployee.class
        ).getSingleResult();
        assertThat(ec, notNullValue());
        assertThat(ec.getId(), notNullValue());
        var dc = em.createQuery(
                "SELECT p FROM PartTimeEmployee p",
                PartTimeEmployee.class
        ).getSingleResult();
        assertThat(dc, notNullValue());
        assertThat(dc.getId(), notNullValue());
        var cs = em.createQuery(
                "SELECT v FROM Employee v",
                Employee.class
        ).getResultList();
        assertThat(cs, hasSize(2));
        cs.forEach(System.out::println);
    }

}
