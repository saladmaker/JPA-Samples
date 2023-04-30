package com.khaled.jpa.learning.basic.mapping;

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
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    
    /*
    name: specifies the corresponding column's name. `helpful when following SGBD naming conventions`.
    nullable: triggers SQL DDL NOT NULL!
    updatable: weahter to include the corresponding column into SQL update statements.
    length :specifies the length of the column. in this case varchar(50)
    */
    @Column(name = "first_name",
            nullable = false,
            updatable = false,
            length = 50
            )
    private String firstName;
    /* 
    fetch: fetch the correspoding column eagrly or lazily(with a new SELECT sql statement)!
    
    optional: is disregarded for perimitive types it may have effect on DDL or it may not no guarantee
    */
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
