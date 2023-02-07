package com.khaled.jpa.learning.with.relationships.tableperclass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.hamcrest.core.IsInstanceOf;
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
public class TablePerClassTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void createPersistenceContext() {//                       
        emf = Persistence.createEntityManagerFactory("TablePerClass");
        em = emf.createEntityManager();
    }

    @AfterAll
    static void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void creation() {
        Department department = new Department("IT");
        PartTimeEmployee pe = new PartTimeEmployee(BigDecimal.ONE,
                "ksdlfs", "ksdlf", "sdfljsdf");
        em.getTransaction().begin();
        em.persist(department);
        pe.setDepartment(department);
        em.persist(pe);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void polymorphicQuery() {
        var employee = em.createQuery(
                "SELECT e FROM Employee e",
                Employee.class
        ).getSingleResult();
        assertThat(employee.getId(), notNullValue());
        assertThat(employee.getDepartment(), notNullValue());
        assertThat(employee.getDepartment(), new IsInstanceOf(Department.class));
    }
    @Test
    @Order(3)
    void specializedQuery() {
        var employee = em.createQuery(
                "SELECT pe FROM PartTimeEmployee pe",
                PartTimeEmployee.class
        ).getSingleResult();
        assertThat(employee.getId(), notNullValue());
        assertThat(employee.getDepartment(), notNullValue());
        assertThat(employee.getDepartment(), new IsInstanceOf(Department.class));
    }
}
