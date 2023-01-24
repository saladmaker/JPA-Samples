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
@Table(name="DEP")
public class Department {
    @Column(name = "DEP_id")
    @GeneratedValue
    @Id private long id;
    @Column(name = "DEP_name")
    private String name;
    public Department() {
    }
    public Department(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Department d) {
            return d.id == this.id;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + '}';
    }
    
    
    
}
