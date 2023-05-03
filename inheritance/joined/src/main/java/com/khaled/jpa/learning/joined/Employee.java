package com.khaled.jpa.learning.joined;

import jakarta.persistence.Column;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String SSN;

    @Column(nullable = false, unique = true)
    private String firstName;

    @Column(nullable = false, unique = true)
    private String lastName;

    public Employee(String SSN, String firstName, String lastName) {
        Objects.requireNonNull(SSN, "SSN must not be null");
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
                && Objects.equals(SSN, e.SSN);
    }

}
