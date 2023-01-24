package com.khaled.jpa.learning.one.to.one.unidirectional;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
/**
 *
 * @author khaled
 */
@Entity
public class Desk {
    @GeneratedValue
    @Id private long id;
    private String floor;

    public Desk(String floor) {
        this.floor = floor;
    }
    public Desk() {
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Desk d) {
            return d.id  == this.id;
        }       
        return false;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
    
}
