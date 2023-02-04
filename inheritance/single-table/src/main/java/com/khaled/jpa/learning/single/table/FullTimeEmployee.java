package com.khaled.jpa.learning.single.table;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

/**
 *
 * @author khaled
 */
@Entity
public class FullTimeEmployee extends Employee {

    private BigDecimal salary;

    public FullTimeEmployee() {
    }

    public FullTimeEmployee(BigDecimal salary,  String SSN, String firstName, String lastName) {
        super(SSN, firstName, lastName);
        this.salary = salary;
    }
    

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    @Override
    public boolean equals(final Object obj){
        return (obj instanceof FullTimeEmployee f)
                && super.equals(f);
    }

}
