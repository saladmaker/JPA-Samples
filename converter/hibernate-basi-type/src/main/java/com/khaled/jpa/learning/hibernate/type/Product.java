package com.khaled.jpa.learning.hibernate.type;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


import java.util.Objects;

import org.hibernate.annotations.Type;

import java.time.Year;


/**
 *
 * @author khaled
 */
@Entity
public class Product {

    @GeneratedValue(strategy = AUTO)
    @Id
    private Long id;
    
    @Type(YearType.class)
    private Year inception;

    @Column(unique = true, updatable= false, nullable =false)
    private String name;

    public Product() {
    }
    public Product(String name, Year inception){
        Objects.requireNonNull(name);
        Objects.requireNonNull(inception);
        this.name = name;
        this.inception = inception;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Year getInception(){
        return inception;
    }
    public void setInception(Year inception){
        this.inception = inception;
    }
    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        if (other instanceof Product d) {
            return (null != id) && id.equals(d.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + '}';
    }

}
