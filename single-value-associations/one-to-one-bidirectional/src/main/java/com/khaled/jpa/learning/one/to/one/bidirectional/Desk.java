package com.khaled.jpa.learning.one.to.one.bidirectional;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
/**
 *
 * @author khaled
 */
@Entity
public class Desk {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private long id;
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
        if (obj instanceof Desk d) {
            return d.id  == this.id;
        }       
        return false;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
}
