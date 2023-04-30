package com.khaled.jpa.learning.one.to.one.bidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Desk {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;

    private String floor;

    @OneToOne(mappedBy = "desk")
    private Employee employee;

    public Desk(String floor) {
        this.floor = floor;
    }
    public Desk() {
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        return (obj instanceof Desk d)
            && null != id
            && Objects.equals(this.id , d.id);
    }
    
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Long getId() {
        return id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Employee getEmployee() {
        return employee;
    }

    void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
