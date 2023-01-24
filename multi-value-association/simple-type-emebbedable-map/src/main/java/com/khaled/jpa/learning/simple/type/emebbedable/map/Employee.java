package com.khaled.jpa.learning.simple.type.emebbedable.map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @ElementCollection
    private Map<String, Task> resposibilities = new HashMap<>();

    public Employee() {
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Employee e)
                && Objects.equals(this.id, e.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Task> getResposibilities() {
        return resposibilities;
    }

    public void setResposibilities(Map<String, Task> resposibilities) {
        this.resposibilities = resposibilities;
    }

    public void setResponsibility(String name, Task task) {
        Objects.requireNonNull(name, "responsibility must not be null");
        Objects.requireNonNull(task, "task set must not be null");
        getResposibilities().put(name, task);
    }

    public void removeResoponsibility(String name) {
        Objects.requireNonNull(name, "reponsibility must not be null");
        getResposibilities().put(name, null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
