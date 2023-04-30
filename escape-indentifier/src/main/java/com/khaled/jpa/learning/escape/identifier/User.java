package com.khaled.jpa.learning.escape.identifier;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author khaled
 */

@Table(name = "\"USER\"")
@Entity
public class User {

    @Id
    private UUID id;

    @Column(name = "\"catalog\"")    
    private String catalog;
    
    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"schema\"")
    private String schema;

    @Column(name = "\"order\"")
    private String order;

    @Column(name = "\"by\"")
    private String by;

    @Column(name = "\"having\"")
    private String having;


    public User() {
        id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof User c)
            && Objects.equals(this.id, c.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + '}';
    }

    public UUID getId() {
        return id;
    }

}
