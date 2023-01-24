package com.khaled.jpa.learning.one.to.one.unidirectional;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
/**
 *
 * @author khaled
 */
@Entity
public class Employee {
    @GeneratedValue
    @Id private long id;
    
    @Column(name = "full_name")
    private String fullName;
    
    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(nullable=false) mandatory 1-1 default is optional 1-0..1
    //@JoinColumn(unique = false) is mondatory to inforce one-to-one
    @JoinColumn(unique = true /* nullable=[false|true] mondatory or optional relationship*/)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee e) {
            return this.id == e.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", desk=" + desk + '}';
    }
    
}
