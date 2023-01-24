package com.khaled.jpa.learning.simple.type.emebbedable.map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SimpleTypeEmbbedableTest {

    private static final Map<String, Task> TEST
            = Map.of("Testing",
                    new Task("using Junit, TestNG for uning testing of the component."));
    private static final Map<String, Task> DEVELOPING
            = Map.of("Developing",
                    new Task("Delivering high quality software design and implementation."));
    private static final Map<String, Task> INTEGRATION_TESTING
            = Map.of("Integration",
                    new Task("Using integration test framework with juint extensions."));
    private static final Map<String, Task> CI_CD
            = Map.of("CI/CD", new Task("using DevOps methodologies and its associating technologies"));

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("setupUCollectionElement");
        em = emf.createEntityManager();
    }

    @BeforeEach
    void createPersistenceContext() {
        emf = Persistence.createEntityManagerFactory("readyUCollectionElement");
        em = emf.createEntityManager();
    }

    @AfterEach
    void destroyPersistenceContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void createEmployee() {
        var employee = new Employee("khaled", "abderrahim");
        var resposibilities = employee.getResposibilities();
        em.getTransaction().begin();
        em.persist(employee);
        resposibilities.putAll(TEST);
        resposibilities.putAll(DEVELOPING);
        em.getTransaction().commit();
    }

    @Test
    @Order(3)
    void createEmployeeTest() {
        var employee = em.createQuery(
                "SELECT e FROM Employee e",
                Employee.class
        ).getSingleResult();
        System.out.println("******lazy fetching ********");
        var actual = employee.getResposibilities();
        Map<String, Task> expected = new HashMap<>();
        expected.putAll(TEST);
        expected.putAll(DEVELOPING);
        assertEquals(actual,expected);
    }
    @Test
    @Order(3)
    void removeResponsibility(){
        var employee = em.createQuery(
                "SELECT e FROM Employee e",
                Employee.class
        ).getSingleResult();
        var responsibilities = employee.getResposibilities();
        String testKey = TEST.keySet().stream().findFirst().get();
        em.getTransaction().begin();
        responsibilities.remove(testKey);
        em.getTransaction().commit();
    }
    @Test
    @Order(4)
    void removeResponsibilityTest() {
        var employee = em.createQuery(
                "SELECT e FROM Employee e",
                Employee.class
        ).getSingleResult();
        var actual = employee.getResposibilities();
        Map<String, Task> expected = new HashMap<>();
        expected.putAll(DEVELOPING);
        assertEquals(actual,expected);
    }
    @Test
    @Order(5)
    void addResposibilties(){
        var employee = em.createQuery(
                "SELECT e FROM Employee e",
                Employee.class
        ).getSingleResult();
        var responsibilities = employee.getResposibilities();
        em.getTransaction().begin();
        responsibilities.putAll(INTEGRATION_TESTING);
        responsibilities.putAll(CI_CD);
        em.getTransaction().commit();
    }
    @Test
    @Order(6)
    void addResposibiltiesTest() {
        var employee = em.createQuery(
                "SELECT e FROM Employee e",
                Employee.class
        ).getSingleResult();
        var actual = employee.getResposibilities();
        Map<String, Task> expected = new HashMap<>();
        expected.putAll(DEVELOPING);
        expected.putAll(CI_CD);
        expected.putAll(INTEGRATION_TESTING);
        assertEquals(expected,actual);
    }
}
