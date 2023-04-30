package com.khaled.jpa.learning.one.to.one.unidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Employee {
    @GeneratedValue
    @Id private Long id;
    
    @Column(name = "full_name")
    private String fullName;
    

    /*
    *   @JoinColumn(nullable=false) nullable is true by default which means (0..1-1) optional relationship 
    *        set it to false to make the relationship mandatory
    *
    *   @JoinColumn(unique = true) unique is false by default you must set it to true to inforce multiplicity <= 1
    */
    @JoinColumn(unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private Desk desk;
    public Employee() {

    }
    public Employee(String fullName) {
        this.fullName = fullName;
    }
    public Employee(String fullName, Desk desk) {
        this.fullName = fullName;
        this.desk = desk;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this) return true;

        return (obj instanceof Employee e)
            && null != id
            && Objects.equals(id, e.id);      

    }
    
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", desk=" + desk + '}';
    }
    
}
