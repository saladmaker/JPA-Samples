package com.khaled.jpa.learning.embeddable.type.collection.map.asvalue;

import com.khaled.jpa.learning.embeddable.type.collection.list.*;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Embeddable
public class Adress {

    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String city;

    Adress() {

    }

    public Adress(String street, String zipCode, String city) {
        Objects.requireNonNull(street, "street can not be null");
        Objects.requireNonNull(zipCode, "zipCode can not be null");
        Objects.requireNonNull(city, "city can not be null");
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, zipCode, street);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Adress a)
                && Objects.equals(a.city, this.city)
                && Objects.equals(a.zipCode, this.zipCode)
                && Objects.equals(a.street, this.street);
    }

    @Override
    public String toString() {
        return "Adress{" + "street=" + street + ", zipCode=" + zipCode + ", city=" + city + '}';
    }

}
