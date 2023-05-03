package com.khaled.jpa.learning.single.table;

import static jakarta.persistence.DiscriminatorType.STRING;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.Objects;

/**
 *
 * @author khaled
 */

@Entity
@DiscriminatorColumn(name = "employee_type", discriminatorType = STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;

    @Column(nullable = false, unique = true)
    private String SSN;

    @Column(nullable = false, unique = true)
    private String firstName;

    @Column(nullable = false, unique = true)
    private String lastName;


    public Employee(String SSN, String firstName, String lastName) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getSSN() {
        return SSN;
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
    public int hashCode() {
        return Objects.hashCode(SSN);
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof Employee e)
                && SSN.equals(e.SSN);
    }

}
