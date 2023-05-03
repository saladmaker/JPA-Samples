package com.khaled.jpa.learning.converter;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.util.Objects;


import javax.money.MonetaryAmount;

import org.hibernate.annotations.CompositeType;


/**
 *
 * @author khaled
 */
@NamedQuery(
    name = "userMonetaryInJPQL",
    query = """
        SELECT p FROM Product p
        WHERE p.money.amount > 12.1212
    """
)
@Entity
public class Product {

    @GeneratedValue(strategy = AUTO)
    @Id
    private Long id;
    
    @CompositeType(MonetaryAmountType.class)
    private MonetaryAmount money;

    @Column(unique = true, updatable= false, nullable =false)
    private String name;

    public Product() {
    }
    public Product(String name, MonetaryAmount money){
        Objects.requireNonNull(name);
        Objects.requireNonNull(money);
        this.name = name;
        this.money = money;
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
