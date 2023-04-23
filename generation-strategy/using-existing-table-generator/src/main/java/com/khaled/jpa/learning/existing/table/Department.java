package com.khaled.jpa.learning.existing.table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.TABLE;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Id;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
@TableGenerator(name = "depGen", table = "MY_GENERATOR", pkColumnName = "GEN_NAME", valueColumnName = "GEN_COUNT", pkColumnValue = "dep_id", allocationSize = 50)
public class Department {

    @GeneratedValue(strategy = TABLE, generator = "depGen")
    @Id
    private Long id;

    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Department d) {
            return d.id == this.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + '}';
    }

}
