package com.khaled.jpa.learning.post.insert;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 *
 * @author khaled
 */
@Entity
public class Person {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;
    
    private String name;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    /**
     * this implementation is transient hostile only the exact transient object
     * * is equal to itself.
     * never equal to null
     * if the @param {other} is the instance of Person then 
     *  if the id is not @code null and it is equal to the p id
     */
    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        //non null and instance of Person
        if (other instanceof Person p) {
            return null != id && id.equals(p.id);
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    

}
