package com.khaled.jpa.learning.uuid;

import static jakarta.persistence.GenerationType.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Department {

    @GeneratedValue(strategy = UUID)
    @Id private UUID id;

    private String name;
    public Department() {
    }
    public Department(String name) {
        this.name = name;
    }

    public UUID getId() {
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
