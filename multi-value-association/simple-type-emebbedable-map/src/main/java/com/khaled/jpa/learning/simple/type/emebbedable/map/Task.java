package com.khaled.jpa.learning.simple.type.emebbedable.map;

import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Embeddable
public class Task {
    private String name;
    public Task(String name) {
        this.name = name;
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Task t)&&
                Objects.equals(name, t.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    
    
    
}
