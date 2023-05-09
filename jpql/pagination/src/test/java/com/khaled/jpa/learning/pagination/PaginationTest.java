package com.khaled.jpa.learning.pagination;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
public class PaginationTest {

    private static final int PAGE_SIZE = 5;
    
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
    void pagination() {
        List<Product> firstPage = pageable(0);
        assertThat(firstPage, hasSize(5));
        List<Product> secondPage = pageable(5);
        assertThat(secondPage, hasSize(4));
        List<Product> thirdPage = pageable(10);
        assertThat(thirdPage, hasSize(0));
    }

    private List<Product> pageable(int offset) {
        /*
         * to use pagination you must use ORDER BY clause because
         * <code>SELECT p FROM Product p</code> is not consistent across executions
         */
        return em.createQuery("SELECT p FROM Product p ORDER BY p.id", Product.class)
                .setFirstResult(offset)
                .setMaxResults(PAGE_SIZE)
                .getResultList();
    }
}
