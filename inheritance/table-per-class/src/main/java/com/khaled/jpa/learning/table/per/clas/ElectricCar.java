package com.khaled.jpa.learning.table.per.clas;

import jakarta.persistence.Entity;


/**
 *
 * @author khaled
 */
@Entity
public class ElectricCar extends Vehicle {

    private int distancePerFulCharge;
    /**
     * seconds taken to reach 100km/s
     */
    private float acceleration;

    public ElectricCar() {
    }

    public ElectricCar(int distancePerFulCharge, float acceleration, String vun,
            String brand, String model, int power) {

        super(vun, brand, model, power);
        this.distancePerFulCharge = distancePerFulCharge;
        this.acceleration = acceleration;
    }

    public int getDistancePerFulCharge() {
        return distancePerFulCharge;
    }

    public void setDistancePerFulCharge(int distancePerFulCharge) {
        this.distancePerFulCharge = distancePerFulCharge;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ElectricCar e)
                && super.equals(e);
    }

    @Override
    public String toString() {
        return "ElectricCar{" + "distancePerFulCharge=" + distancePerFulCharge + ", acceleration=" + acceleration + '}';
    }
    
}
