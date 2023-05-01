package com.khaled.jpa.learning.natural.id;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Person {

    @GeneratedValue(strategy = AUTO)
    @Id
    private Long id;
    
    @Column(unique = true, nullable = false, updatable = false)
    private String nationalId;

    Person() {
    }

    public Person(String nationalId) {
        Objects.requireNonNull(nationalId);
        this.nationalId = nationalId;
    }

    public Long getId() {
        return id;
    }


    @Override
    public boolean equals(Object other) {
        return (other instanceof Person p)
            && Objects.equals(this.nationalId, p.nationalId);
        
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nationalId);
    }

}
