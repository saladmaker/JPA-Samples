package com.khaled.jpa.learning.many.to.many.unidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author khale
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManyToManyUnidirectionalTest {

    private static final Employee KHALED
            = new Employee("khaled", "abderrahim");
    private static final Employee FOU
            = new Employee("fou", "bar");
    private static final Resposibility DEVELOPEMENT
            = new Resposibility("Development", "Developing components");
    private static final Resposibility TESTING
            = new Resposibility("integration testing",
                    "testing components interaction");
    private static final Resposibility SYSTEM
            = new Resposibility("system testing",
                    "testing the overall system");
    private static final Resposibility DEVOPS
            = new Resposibility("devops",
                    "applying continous integration and continous delivery");
    private static final Resposibility QA
            = new Resposibility("quality assurance",
                    "assuring the system builtin quality is good");
    private static final Employee DUKE
            = new Employee("Duke", "java duke");

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupUManyToManyUnidirectional");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyUManyToManyUnidirectional");
        em = emf.createEntityManager();
    }

    @AfterEach
    void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void creation() {
        em.getTransaction().begin();
        em.persist(KHALED);
        em.persist(FOU);
        em.persist(DUKE);
        KHALED.addResponsibility(DEVELOPEMENT);
        KHALED.addResponsibility(TESTING);
        DUKE.addResponsibility(SYSTEM);
        FOU.addResponsibility(DEVOPS);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void creatinTest() {
        var khaled = em.createQuery(
                """
                SELECT e FROM Employee e WHERE e.id = :id""",
                Employee.class
        ).setParameter("id", KHALED.getId())
                .getSingleResult();
        System.out.println("-*-*-*lazy loading-*-*-*");
        var actualProjects = khaled.getResponsibilities();
        assertEquals(Set.of(DEVELOPEMENT, TESTING), actualProjects);
    }

    @Test
    @Order(3)
    void dukeCreationTest() {
        var duke = em.createQuery(
                """
                SELECT e FROM Employee e WHERE e.id = :id""",
                Employee.class
        ).setParameter("id", DUKE.getId())
                .getSingleResult();
        System.out.println("-*-*-*lazy loading-*-*-*");
        var actualProjects = duke.getResponsibilities();
        assertEquals(Set.of(SYSTEM), actualProjects);
    }
    @Test
    @Order(4)
    void removeResponsibility(){
        var khaled =em.createQuery(
                """
                SELECT e FROM Employee e WHERE e.id = :id""",
                Employee.class
        ).setParameter("id", KHALED.getId())
                .getSingleResult();
        em.getTransaction().begin();
        khaled.removeResponsibility(TESTING);
        em.getTransaction().commit();
    }
    @Test
    @Order(5)
    void removeResponsibilityTest(){
        var khaled =em.createQuery(
                """
                SELECT e FROM Employee e WHERE e.id = :id""",
                Employee.class
        ).setParameter("id", KHALED.getId())
                .getSingleResult();
        var responsibilities = khaled.getResponsibilities();
        assertEquals(responsibilities, Set.of(DEVELOPEMENT));
    }
    @Test
    @Order(6)
    void addResponsibility(){
        var duke =em.createQuery(
                """
                SELECT e FROM Employee e WHERE e.id = :id""",
                Employee.class
        ).setParameter("id", DUKE.getId())
                .getSingleResult();
        em.getTransaction().begin();
        duke.addResponsibility(TESTING);
        em.getTransaction().commit();
    }
    @Test
    @Order(7)
    void addResponsibilityTest(){
        var duke =em.createQuery(
                """
                SELECT e FROM Employee e WHERE e.id = :id""",
                Employee.class
        ).setParameter("id", DUKE.getId())
                .getSingleResult();
        var responsibilities = duke.getResponsibilities();
        assertEquals(responsibilities, Set.of(SYSTEM,TESTING));
    }
}
