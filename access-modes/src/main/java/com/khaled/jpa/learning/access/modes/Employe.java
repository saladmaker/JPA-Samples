package com.khaled.jpa.learning.access.modes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author khaled
 */
@Entity
// this annotation is unicessary because we aren't mixing access
//@Access(AccessType.PROPERTY) 
/*
CREATE TABLE EMPLOYE (ID VARCHAR NOT NULL, first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL, salary NUMERIC(38), PRIMARY KEY (ID))
*/
public class Employe {
    
    private UUID id;
    private String firstName;
    private String lastName;
    private BigDecimal wage;

    public Employe() {
    }
    
    
    public Employe(String firstName, String lastName, BigDecimal wage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.wage = wage;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "salary")
    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Employe e) {
            return Objects.equals(getId(), e.getId());
        }
       return false;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", wage=" + wage + '}';
    }
}
