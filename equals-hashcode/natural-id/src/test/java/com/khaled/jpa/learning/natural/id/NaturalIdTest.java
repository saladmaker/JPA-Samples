package com.khaled.jpa.learning.natural.id;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NaturalIdTest {

        private static EntityManagerFactory emf;
    private static EntityManager em;

    static Set<Person> entities;
    static Person person;
    static int transientHashCode;

    @BeforeAll
    static void generateDatabase() {
        emf = Persistence.createEntityManagerFactory("PU");
    }
    @AfterAll
    static void closeDatabase(){
        emf.close();
    }

    private void runTransactionally(Runnable r) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        r.run();
        em.getTransaction().commit();
        em.close();
    }

 

    @Test
    @Order(1)
    void Transient() {
        entities = new HashSet<>();
        person = new Person("212");
        transientHashCode = person.hashCode();
        var person2 = new Person("232");


        assertThat(person.equals(person2), is(false));
        assertThat(person2.equals(person), is(false));
        assertThat(entities, not(contains(person)));

        entities.add(person);
        assertConsistency(person);

    }

    @Test
    @Order(2)
    void persisted() {
        runTransactionally(() -> {

            em.persist(person);
            em.flush();

            assertConsistency(person);

        });
    }

    @Test
    @Order(3)
    void detached() {
        assertConsistency(person);
    }

    @Test
    @Order(4)
    void proxy() {
        runTransactionally(() -> {
            Person proxy = em.getReference(Person.class, person.getId());
            assertConsistency(proxy);
        });
    }

    @Test
    @Order(5)
    void merged() {
        runTransactionally(() -> {
            em.find(Person.class, person.getId());
            Person merged = em.merge(person);

            assertConsistency(merged);
        });
        runTransactionally(() -> {
            Person managed = em.find(Person.class, person.getId());

            assertConsistency(managed);
        });
        runTransactionally(() -> {
            Person proxy = em.getReference(Person.class, person.getId());

            assertConsistency(proxy);
        });
    }

    @Test
    @Order(6)
    void deleted() {

        runTransactionally(() -> {
            var deleted = em.getReference(Person.class, person.getId());

            assertConsistency(deleted);

            em.remove(deleted);

            assertConsistency(deleted);
        });
    }

    private static void assertConsistency(Person entity) {
        assertThat(transientHashCode, is(entity.hashCode()));
        assertThat(entity, is(person));
        assertThat(person, is(entity));
        assertThat(entities, contains(entity));
    }

}
