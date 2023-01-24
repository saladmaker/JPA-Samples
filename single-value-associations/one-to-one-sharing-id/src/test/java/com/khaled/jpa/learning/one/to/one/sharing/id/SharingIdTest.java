package com.khaled.jpa.learning.one.to.one.sharing.id;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author khaled
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SharingIdTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    static void generatingDatabase() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("setupSharingId");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * tearing down the database after all test by closing the
     * entityManagerFactory
     */
    @BeforeEach
    void createContext() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("readySharingId");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void clearContext() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }

    @Test
    @Order(1)
    void cascadePersistTest() {
        char[] details = "sharing id in a mandatory one-to-one relationships"
                .toCharArray();
        PostDetail detail = new PostDetail(details);
        Post post = new Post("jpa");
        post.setDetail(detail);
        detail.setPost(post);
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
    }

    @Test
    @Order(2)
    void lazyFetchTest() {
        System.out.println("-*-*-*-lazy fetch*-**-*");
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class)
                .getSingleResult();
        System.out.println("-**-*-*-accessing lazy attribut*-*-*-*-");
        
        PostDetail detail = ourPost.getDetail();
        System.out.println("-*-*-*-*-*-*-*accessing lazy content");
        detail.getContent();
        System.out.println("-**-*-*-lazy fetching*-*-*-*-");
    }
    @Test
    @Order(3)
    void cascadeRemoveTest() {
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class)
                .getSingleResult();
        System.out.println("-*-*-*-* removing-*-*-*");
        entityManager.getTransaction().begin();
        entityManager.remove(ourPost);
        entityManager.getTransaction().commit();
    }
}
