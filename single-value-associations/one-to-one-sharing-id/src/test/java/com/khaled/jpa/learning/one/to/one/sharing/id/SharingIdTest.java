package com.khaled.jpa.learning.one.to.one.sharing.id;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
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

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    static void generatingDatabase() {
        emf = Persistence.createEntityManagerFactory("setupSharingId");
        emf.createEntityManager().close();
        emf.close();
        
    }

    @BeforeEach
    void createContext() {
        emf = Persistence.createEntityManagerFactory("readySharingId");
        em = emf.createEntityManager();
    }

    @AfterEach
    void clearContext() {
        em.close();
        emf.close();
    }

    @Test
    @Order(1)
    void cascadePersistTest() {
        char[] details = "sharing id in a mandatory one-to-one relationships"
                .toCharArray();
        PostDetail detail = new PostDetail(details);
        Post post = new Post("jpa");
        post.addDetail(detail);
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
    }

    @Test
    @Order(2)
    void lazyFetchTest() {
        Post ourPost = em.createQuery("SELECT p FROM Post p",
                Post.class)
                .getSingleResult();

        System.out.println("-**-*-*-accessing lazy association PostDetail*-*-*-*-");
        PostDetail detail = ourPost.getDetail();

        System.out.println("-*-*-*-*-*-*-*accessing lazy content");
        detail.getContent();
    }
    @Test
    @Order(3)
    void cascadeRemoveTest() {
        Post ourPost = em.createQuery("SELECT p FROM Post p",
                Post.class)
                .getSingleResult();
        em.getTransaction().begin();
        em.remove(ourPost);
        em.getTransaction().commit();
    }
}
