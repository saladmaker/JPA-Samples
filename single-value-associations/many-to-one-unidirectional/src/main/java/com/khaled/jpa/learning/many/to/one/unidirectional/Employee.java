package com.khaled.jpa.learning.many.to.one.unidirectional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Employee {

    @GeneratedValue
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    /*
    *   @JoinColumn(nullable = false) to inforce that the relationship
    *       is mondatory from the perspective of the owner entity
    *   every row of Employee must associated with an existing row of Department
    */
    @JoinColumn(name = "DEP_ID", nullable = false)
    @ManyToOne
    Department department;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public Long getId() {
        return id;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        return (other instanceof Employee e)
            && null != id
            && Objects.equals(id, e.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department=" + department + '}';
    }

}
