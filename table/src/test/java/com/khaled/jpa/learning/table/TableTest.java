package com.khaled.jpa.learning.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TableTest {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Table");
    private EntityManager em;

    /**
     * tearing down the database after all test by closing the emf
     */
    @AfterAll
    static void tearDown() {
        emf.close();
    }

    @BeforeEach
    void createContext() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void clearContext() {
        em.close();
    }

    @Test
    @Order(1)
    void queryTest() {
        List<Professor> actualProfessors = em.createQuery("SELECT p FROM Professor p ", Professor.class)
                .getResultList();
        assertThat(actualProfessors, hasSize(1));

        var actualStudents = em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList();
        assertThat(actualStudents, hasSize(2));
    }

    @Test
    @Order(2)
    void insertTest() {
        em.getTransaction().begin();
        Student student = new Student("kh", "kdf", LocalDate.of(1994,11,26));
        em.persist(student);
        Professor professor = new Professor("dsf", "fsdf", "dsfdsf");
        em.persist(professor);
        em.getTransaction().commit();
    }
}
