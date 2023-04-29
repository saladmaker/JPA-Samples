package com.khaled.jpa.learning.access.modes;

import static jakarta.persistence.GenerationType.UUID;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author khaled
 */
@Entity
/*
*   this annotation is unicessary because we are not mixin access
*   this access type is defaulted to field access because we use @Id
*       annotation on the field
*/
public class Customer {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;
    
   
    @Column(name = "first_name", nullable = false, updatable = false, length = 50)
    private String firstName;
    
    @Basic(fetch = FetchType.EAGER, optional = false)
    private String lastName;

    private LocalDate dateOfBirth;

    public Customer() {
    }

    public Customer(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        return (other instanceof Customer c)
            && id != null && id.equals(c.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + '}';
    }

    public UUID getId() {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
