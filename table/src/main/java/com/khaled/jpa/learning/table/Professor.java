
package com.khaled.jpa.learning.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 *
 * @author khaled
 */
/*
 * schema = HR
 * table = PROF
 * firstName = first_name VARCHAR(50) NOT NULL
 * lastName = last_name VARCHAR(50) NOT NULL
 * socialSecurityNumber = SSN VARCHAR(50) NOT NULL UNIQUE
 */

@Table(name = "PROF", schema = "HR")
@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROF_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "SSN", unique = true)
    private String socialSecurityNumber;

    public Professor(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Professor() {
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

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        return (other instanceof Professor p)
                && null != id
                && Objects.equals(id, p.id);
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
                + ", socialSecurityNumber=" + socialSecurityNumber + '}';
    }

}
