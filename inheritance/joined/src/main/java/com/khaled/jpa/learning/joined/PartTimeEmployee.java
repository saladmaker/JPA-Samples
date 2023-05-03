package com.khaled.jpa.learning.joined;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

/**
 *
 * @author khaled
 */

@Entity
public class PartTimeEmployee extends Employee{
    private BigDecimal rate;

    
    public PartTimeEmployee() {
    }

    public PartTimeEmployee(BigDecimal rate,  String SSN, String firstName, String lastName) {
        super(SSN, firstName, lastName);
        this.rate = rate;
    }
    
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PartTimeEmployee p)
                && super.equals(p);
    }
    
}
