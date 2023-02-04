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
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// use abstract if you don't want vehicle to be mapped to its own class.
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

    public Vehicle() {
    }
    
    public Vehicle(String vun,String brand, String model, int power) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleUniqueNumber() {
        return vehicleUniqueNumber;
    }

    public void setVehicleUniqueNumber(String vehicleUniqueNumber) {
        this.vehicleUniqueNumber = vehicleUniqueNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.vehicleUniqueNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Vehicle v)
                && Objects.equals(v.vehicleUniqueNumber, this.vehicleUniqueNumber);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", vehicleUniqueNumber=" +
                vehicleUniqueNumber + ", brand=" + brand + ", model=" +
                model + ", power=" + power + '}';
    }
    
}
