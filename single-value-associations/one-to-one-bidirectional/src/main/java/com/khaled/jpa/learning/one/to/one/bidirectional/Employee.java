package com.khaled.jpa.learning.one.to.one.bidirectional;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Objects;
/**
 *
 * @author khaled
 */
@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private long id;
    
    @Column(name = "full_name")
    private String fullName;
    
    @OneToOne
    //@JoinColumn(nullable=false) mandatory 1-1 default is optional 1-0..1
    @JoinColumn(unique = true)// is mondatory to inforce 0..1 multiplecity
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
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Employee e)&& e.id == this.id;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName=" + fullName + ", desk=" + desk + '}';
    }
    
}
