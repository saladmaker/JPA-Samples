package com.khaled.jpa.learning.iheriting.non.entity.classes;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

/**
 *
 * @author khaled
 */

@Entity
public class SomeEntity extends NoEntityClasses {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int persisted;

    public SomeEntity() {
        
    }

    public SomeEntity(int value) {
        this.persisted = value;
        
    }
    public SomeEntity(int value, int other){ 
        super(other);
        this.persisted = value;  
    }

    public Long getId() {
        return id;
    }

    public int getPersisted() {
        return persisted;
    }

    public int transientCalculation(){
        return super.transientState * persisted;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        return (obj instanceof SomeEntity se)
                && null != id
                && Objects.equals(id, se.id);
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

}
