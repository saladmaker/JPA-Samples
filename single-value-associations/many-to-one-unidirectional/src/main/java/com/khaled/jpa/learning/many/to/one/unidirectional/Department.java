package com.khaled.jpa.learning.many.to.one.unidirectional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 *
 * @author khaled
 */

@Entity
public class Department {

    @GeneratedValue
    @Id private Long id;

    private String name;

    public Department() {
    }
    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        return (other instanceof Department d)
            && null != id
            && Objects.equals(id, d.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + '}';
    }
   
}
