package com.khaled.jpa.learning.with.relationships.joined;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

/**
 *
 * @author khaled
 */

@Entity
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyRate;

    public PartTimeEmployee(BigDecimal hourlyRate, String SSN, String firstName, String lastName) {
        super(SSN, firstName, lastName);
        this.hourlyRate = hourlyRate;
    }

    PartTimeEmployee() {
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof PartTimeEmployee p)
                && super.equals(p);
    }

    public BigDecimal getHourlyRate(){
        return hourlyRate;
    }
}
