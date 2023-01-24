package com.khaled.jpa.learning.access.modes;

//import jakarta.persistence.Access;
//import jakarta.persistence.AccessType;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
// this annotation is unicessary because we are not mixin access 
//@Access(AccessType.FIELD)
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    
   
    @Column(name = "first_name",
            nullable = false,
            updatable = false,
            length = 50
            )
    private String firstName;
    
    @Basic(fetch = FetchType.EAGER,
            optional = false)
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
        if(other instanceof Customer c){
            return Objects.equals(c.getId(), getId());
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + '}';
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
}
