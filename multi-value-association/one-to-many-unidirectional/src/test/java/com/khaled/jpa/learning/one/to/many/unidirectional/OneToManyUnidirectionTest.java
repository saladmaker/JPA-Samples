package com.khaled.jpa.learning.one.to.many.unidirectional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
public class OneToManyUnidirectionTest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    static void generatingDatabase() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("setupUOneToMany");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * tearing down the database after all test by closing the
     * entityManagerFactory
     */
    @BeforeEach
    void createContext() {
        entityManagerFactory
                = Persistence.createEntityManagerFactory("readyUOneToMany");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void clearContext() {
        this.entityManager.close();
        this.entityManagerFactory.close();
    }
    
    @Test
    @Order(1)
    void test(){
        var post = new Post("JPA one to many unidirectional mapping.");
        var postcontent = """
        one to many unidirectional mapping is when the owner side contains
                          a Collection of the target entity. you can use 
                          Collection, List, Set, Map,...etc.
                          but it isn't the ideal mapping solution!
        """.toCharArray();
        post.setContent(postcontent);
        
        var firstComment = new Comment("using Map as type is overcomplication I think!");
        var secondComment = new Comment("bidirectional mapping is the way to go!");
        var thirdComment = new Comment("cadcading all operation is dangerous feature"
                + "that must be used judiciously!");
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        post.addComment(firstComment);
        post.addComment(secondComment);
        post.addComment(thirdComment);
        entityManager.getTransaction().commit();
    }
    @Test
    @Order(2)
    void lazyFetchingTest(){
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class).getSingleResult();
        System.out.println("-*-*accessing comments-*-*");
        var ourComments = ourPost.getComments();
        Assertions.assertFalse(ourComments.isEmpty());
        Assertions.assertEquals(3,ourComments.size());
    }
    @Test
    @Order(3)
    void nonTransactionalDeletionSetup(){
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class).getSingleResult();
        var ourComments = ourPost.getComments();
        ourComments.clear();
        Assertions.assertTrue(ourComments.isEmpty());
        Assertions.assertEquals(0,ourComments.size());
    }
    @Test
    @Order(4)
    void nonDeletionTest(){
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class).getSingleResult();
        var ourComments = ourPost.getComments();
        Assertions.assertFalse(ourComments.isEmpty());
        Assertions.assertEquals(3,ourComments.size());
    }
    @Test
    @Order(5)
    void TransactionalDeletionSetup(){
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class).getSingleResult();
        var ourComments = ourPost.getComments();
        Assertions.assertEquals(3,ourComments.size());
        ourComments.removeIf((Comment c)->c.getId().equals(2L));
        Assertions.assertEquals(2,ourComments.size());
        entityManager.getTransaction().begin();
        entityManager.getTransaction().commit();
    }
    @Test
    @Order(6)
    void TransactionalDeletionTest(){
        Post ourPost = entityManager.createQuery("SELECT p FROM Post p",
                Post.class).getSingleResult();
        var ourComments = ourPost.getComments();
        Assertions.assertEquals(2,ourComments.size());
    }
}
