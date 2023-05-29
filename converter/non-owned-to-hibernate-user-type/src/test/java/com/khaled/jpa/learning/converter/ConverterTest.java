package com.khaled.jpa.learning.converter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConverterTest {

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
    void persistDepartmentTest() {
        MonetaryAmount price = Monetary.getDefaultAmountFactory().setCurrency("EUR").setNumber(234).create();
        Product tree = new Product("The Tree Hollyday", price);
        em.getTransaction().begin();
        em.persist(tree);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void retrieveTest() {
        em.getTransaction().begin();
        var product = em.createQuery("SELECT h FROM Product h", Product.class)
                .getSingleResult();
                
        assertThat(product.getPrice(), is(Money.of(BigDecimal.valueOf(234), "EUR")));
        assertThat(product.getPrice().toString(), is("EUR 234.00"));
        em.getTransaction().commit();
    }
    @Test
    @Order(3)
    void jpqlTest() {
        em.getTransaction().begin();
        var product = em.createNamedQuery("userMonetaryInJPQL", Product.class)
                .getSingleResult();
                
        assertThat(product.getPrice(), is(Money.of(BigDecimal.valueOf(234), "EUR")));
        assertThat(product.getPrice().toString(), is("EUR 234.00"));
        em.getTransaction().commit();
    }
}
