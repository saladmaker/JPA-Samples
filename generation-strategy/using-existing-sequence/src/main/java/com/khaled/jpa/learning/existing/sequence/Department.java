package com.khaled.jpa.learning.existing.sequence;

import static jakarta.persistence.GenerationType.SEQUENCE;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
@SequenceGenerator(name = "depSeq", sequenceName = "DEP_SEQ", allocationSize = 50)
public class Department {

    @GeneratedValue(strategy = SEQUENCE, generator = "depSeq")
    @Id
    private Long id;

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
