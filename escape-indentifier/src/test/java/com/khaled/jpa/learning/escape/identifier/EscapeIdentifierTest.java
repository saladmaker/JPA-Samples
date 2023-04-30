package com.khaled.jpa.learning.escape.identifier;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EscapeIdentifierTest {
    
    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("PU");

    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();


    /**
     * tearing down the database after all test by closing the entityManagerFactory
     */
    @AfterAll
    static void tearDown(){
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    void userTest() {
        User user = new User();
        entityManager.getTransaction().begin();

        entityManager.persist(user);
        entityManager.getTransaction().commit();

        var actualId = user.getId();
        assertNotNull(user.getId());

        var actualuser = entityManager.find(User.class, actualId);
        assertEquals(user, actualuser);
    }

}
