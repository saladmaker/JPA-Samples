package com.khaled.jpa.learning.hibernate.pooled.sequence;

import static jakarta.persistence.GenerationType.SEQUENCE;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import java.util.Objects;
/**
 *
 * @author khaled
 */
@Entity
public class Department {
    @SequenceGenerator(name = "dep_seq", sequenceName = "dep_seq" , allocationSize = 4)
    @GeneratedValue(strategy = SEQUENCE, generator = "dep_seq")
    @Id private Long id;

    public Department() {
    }

    public Long getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        return (other instanceof Department d) && (null != id && id.equals(d.id));
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + '}';
    }
   
}
