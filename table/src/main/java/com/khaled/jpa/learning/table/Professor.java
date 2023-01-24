
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
schema = HR
table = PROF
firstName = first_name VARCHAR(50) NOT NULL
lastName = last_name VARCHAR(50) NOT NULL
socialSecurityNumber = SSN VARCHAR(50) NOT NULL UNIQUE
*/
@Entity
@Table(name = "PROF", schema = "HR")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROF_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "SSN", unique = true )
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

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Professor pf) {
            return Objects.equals(pf.getId(), id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", socialSecurityNumber=" + socialSecurityNumber + '}';
    }
    
    
    
}
