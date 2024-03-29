package com.khaled.jpa.learning.with.relationships.tableperclass;

import com.khaled.jpa.learning.with.relationships.Department;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

/**
 *
 * @author khaled
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String SSN;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @ManyToOne
    @JoinColumn(name = "DEP_ID")
    private Department workfor;
    
    public Employee() {

    }

    public Employee(String SSN, String firstName, String lastName) {
        Objects.requireNonNull(SSN, "SSN must not be null");
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public Department getDepartment() {
        return workfor;
    }

    public void setDepartment(Department department) {
        this.workfor = department;
    }
    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
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


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Employee e)
                && Objects.equals(this.SSN, e.SSN);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(SSN);
    }
    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", SSN=" + SSN + ", firstName=" + firstName + ", lastName=" + lastName + '}';
    }

}
