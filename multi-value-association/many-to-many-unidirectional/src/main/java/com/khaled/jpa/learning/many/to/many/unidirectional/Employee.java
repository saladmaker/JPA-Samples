package com.khaled.jpa.learning.many.to.many.unidirectional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    //lazy by default
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "RESPONSIBILITY_ID")
    )
    private Set<Resposibility> responsibilities = new HashSet<>();

    public Employee() {
    }

    public Employee(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Employee e)
                && Objects.equals(e.id, this.id);
    }

    public void addResponsibility(final Resposibility resposibility) {
        getResponsibilities().add(resposibility);
    }

    public void removeResponsibility(final Resposibility resposibility) {
        getResponsibilities().remove(resposibility);
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

    public Set<Resposibility> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(Set<Resposibility> resposibilities) {
        this.responsibilities = resposibilities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", projects=" + responsibilities + '}';
    }
}
