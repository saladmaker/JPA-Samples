
package com.khaled.jpa.learning.pagination;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

/**
 *
 * @author khaled
 */

@Table(name = "Products")
@Entity
public class Product {

    @Id
    @Column(name = "prod_id", insertable = true, updatable = false, nullable = false)
    private String id;

    @Column(name = "vend_id")
    private String vendor;

    @Column(name = "prod_name")
    private String name;

    @Column(name = "prod_price")
    private double price;

    @Column(name = "prod_desc")
    private String desc;

    protected Product(){

    }
    
    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Product p)
                && Objects.equals(id, p.id);
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + ", vendor=" + vendor + ", name=" + name
                + ", price=" + price + ", desc=" + desc + '}';
    }

}
