package com.khaled.jpa.learning.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author khaled
 */

public class TableTest {
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("Table");
    private EntityManager entityManager;
    /**
     * tearing down the database after all test by closing the entityManagerFactory
     */
    @AfterAll
    static void tearDown(){
        entityManagerFactory.close();
    }
    @BeforeEach
    void createContext() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    
    @AfterEach
    void clearContext() {
        this.entityManager.close();
        
    }
    @Test
    void queryTest(){
        List<Professor> actualProfessors = entityManager.createQuery("SELECT p FROM Professor p ",
                Professor.class)
                .getResultList();
        actualProfessors.forEach(System.out::println);
        Assertions.assertEquals(false,actualProfessors.isEmpty());
    }
}
