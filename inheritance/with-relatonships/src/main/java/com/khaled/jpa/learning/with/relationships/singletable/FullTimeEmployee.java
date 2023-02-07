package com.khaled.jpa.learning.with.relationships.singletable;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

/**
 *
 * @author khaled
 */
@Entity
public class FullTimeEmployee extends Employee {

    private BigDecimal annualWage;

    public FullTimeEmployee(BigDecimal annualWage, String SSN, String firstName, String lastName) {
        super(SSN, firstName, lastName);
        this.annualWage = annualWage;
    }

    public FullTimeEmployee() {
    }

    public BigDecimal getAnnualWage() {
        return annualWage;
    }

    public void setAnnualWage(BigDecimal annualWage) {
        this.annualWage = annualWage;
    }

    @Override
    public boolean equals(final Object obj) {
        return (obj instanceof FullTimeEmployee f)
                && super.equals(f);
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" + "annualWage=" + annualWage + '}';
    }

}
