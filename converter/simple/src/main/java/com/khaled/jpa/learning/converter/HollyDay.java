package com.khaled.jpa.learning.converter;

import static jakarta.persistence.GenerationType.AUTO;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

import java.time.MonthDay;

/**
 *
 * @author khaled
 */
@Entity
public class HollyDay {

    @GeneratedValue(strategy = AUTO)
    @Id
    private Long id;
    
    @Convert(converter = MonthDayConverter.class)
    private MonthDay hollyDay;

    @Column(unique = true, updatable= false, nullable =false)
    private String name;

    public HollyDay() {
    }
    public HollyDay(String name, MonthDay hollyDay){
        this.name = name;
        this.hollyDay = hollyDay;
    }

    public Long getId() {
        return id;
    }

    public MonthDay getDay() {
        return hollyDay;
    }
    public String getName() {
        return name;
    }

    public void setDay(MonthDay hollyDay) {
        this.hollyDay = hollyDay;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        if (other instanceof HollyDay d) {
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
        return "Department{" + "id=" + id + ", name=" + name + '}';
    }

}
