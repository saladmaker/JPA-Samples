package com.khaled.jpa.learning.one.to.one.unidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Desk {

    @GeneratedValue
    @Id private Long id;

    private String floor;

    public Desk(String floor) {
        this.floor = floor;
    }
    public Desk() {
    }
    
    @Override
    public boolean equals(Object obj) {

        if(obj == this) return true;

        return (obj instanceof Desk d)
            && null != id
            && Objects.equals(id, d.id);      

    }
    
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
    
}
