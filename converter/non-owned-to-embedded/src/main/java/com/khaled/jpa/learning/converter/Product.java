package com.khaled.jpa.learning.converter;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

import java.util.Objects;


import javax.money.MonetaryAmount;


/**
 *
 * @author khaled
 */
@Entity
public class Product {

    @GeneratedValue(strategy = AUTO)
    @Id
    private Long id;
    
    @Transient
    private MonetaryAmount money;

    @Embedded
    private Price price;

    @Column(unique = true, updatable= false, nullable =false)
    private String name;

    public Product() {
    }
    public Product(String name, MonetaryAmount money){
        Objects.requireNonNull(name);
        Objects.requireNonNull(money);
        this.name = name;
        this.price = Price.ofMoney(money);
        this.money = money;
    }
    @PostLoad
    public void onLoad(){
        this.money = price.toMonetaryAmount();
    }
    public Long getId() {
        return id;
    }

    public MonetaryAmount getPrice() {
        return money;
    }
    public String getName() {
        return name;
    }

    public void setPrice(MonetaryAmount money) {
        this.money = money;
        this.price = Price.ofMoney(money);
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
