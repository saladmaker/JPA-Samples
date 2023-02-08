package com.khaled.jpa.learning.embeddable.type.collection.map.asvalue;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    private String firstName;
    private String lastName;
    @ElementCollection
    @CollectionTable(
            name = "ADRESS",
            joinColumns = @JoinColumn(name = "CUSTOMER_ID")
    )
    private Map<String, Adress> adresses = new HashMap<>();

    public Customer() {
    }

    public Customer(final String firstName, final String lastName, final String email) {
        Objects.requireNonNull(email, "email must not be null");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(Map<String, Adress> adresses) {
        this.adresses = adresses;
    }

    public void addAdress(final String type, final Adress adress) {
        getAdresses().put(type, adress);
    }

    public void removeAdress(final String type) {
        getAdresses().remove(type);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Customer c)
                && Objects.equals(c.email, email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", adresses=" + adresses + '}';
    }

}
