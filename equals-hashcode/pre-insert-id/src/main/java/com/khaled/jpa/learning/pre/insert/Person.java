package com.khaled.jpa.learning.pre.insert;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author khaled
 */
@Entity
public class Person {

    @Id
    private UUID id;
    

    public Person() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    /**
     * this implementation is relient on the the initialized id
     *
     */
    @Override
    public boolean equals(Object other) {
        
        return (other instanceof Person p)
            && Objects.equals(id, p.id);
        
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
}
