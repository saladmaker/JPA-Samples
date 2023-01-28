package com.khaled.jpa.learning.many.to.many.unidirectional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Resposibility {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    private String Description;

    public Resposibility() {
    }

    public Resposibility(String name, String description) {
        this.Description = description;
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Resposibility p)
                && Objects.equals(p.name, this.name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", Description=" + Description + '}';
    }
}
