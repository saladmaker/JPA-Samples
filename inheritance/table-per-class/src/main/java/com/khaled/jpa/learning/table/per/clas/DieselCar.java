
package com.khaled.jpa.learning.table.per.clas;

import jakarta.persistence.Entity;

/**
 *
 * @author khaled
 */
@Entity
public class DieselCar extends Vehicle{
    private int maxSpeed;
    private int numberOfCylinder;

    public DieselCar(int maxSpeed, int numberOfCylinder, String vun, String brand, String model, int power) {
        super(vun, brand, model, power);
        this.maxSpeed = maxSpeed;
        this.numberOfCylinder = numberOfCylinder;
    }

    public DieselCar() {
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getNumberOfCylinder() {
        return numberOfCylinder;
    }

    public void setNumberOfCylinder(int numberOfCylinder) {
        this.numberOfCylinder = numberOfCylinder;
    }
    @Override
    public boolean equals(Object obj){
        return (obj instanceof DieselCar d)
                && super.equals(d);
    }

    @Override
    public String toString() {
        return "DieselCar{" + "maxSpeed=" + maxSpeed + ", numberOfCylinder=" + numberOfCylinder + '}';
    }
    
}
