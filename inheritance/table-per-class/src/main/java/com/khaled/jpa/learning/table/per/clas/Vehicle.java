package com.khaled.jpa.learning.table.per.clas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.Objects;

/**
 *
 * @author khaled
 */

// use abstract if you don't want vehicle to be mapped to its own class.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Vehicle {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicleUniqueNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int power;

    protected Vehicle() {
    }

    public Vehicle(String vun, String brand, String model, int power) {
        Objects.requireNonNull(vun);
        this.vehicleUniqueNumber = vun;
        this.brand = brand;
        this.model = model;
        this.power = power;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleUniqueNumber() {
        return vehicleUniqueNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vehicleUniqueNumber);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Vehicle v)
                && vehicleUniqueNumber.equals(v.vehicleUniqueNumber);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", vehicleUniqueNumber=" +
                vehicleUniqueNumber + ", brand=" + brand + ", model=" +
                model + ", power=" + power + '}';
    }
    
}
