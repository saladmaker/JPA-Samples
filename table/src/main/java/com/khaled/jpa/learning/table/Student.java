
package com.khaled.jpa.learning.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author khaled
 */
/*
schema = PED
table = STD
firstName = first_name VARCHAR(50) NOT NULL
lastName = last_name VARCHAR(50) NOT NULL
*/
@Entity
@Table(name = "STD", schema = "PED")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "DOF", unique = true )
    private LocalDate dateOfBirth;

    public Student (String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Student() {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Student pf) {
            return Objects.equals(pf.getId(), id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName + ", socialSecurityNumber=" +
                dateOfBirth + '}';
    }
      
}
