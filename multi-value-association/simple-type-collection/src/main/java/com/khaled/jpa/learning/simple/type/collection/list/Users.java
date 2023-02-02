package com.khaled.jpa.learning.simple.type.collection.list;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;
    @Column(nullable = true, unique = true)
    private String email;
    private String password;
    //lazy by defaults
    @ElementCollection
    @CollectionTable(
            name = "INTREST",
            joinColumns = @JoinColumn(name = "USERS_ID")
            )
    @Column(name = "INTREST_NAME", nullable = false)
    private List<String> intrests= new ArrayList<>();

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users() {
    }
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Users u)&&
                Objects.equals(this.email, u.email);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getIntrests() {
        return intrests;
    }

    public void setIntrests(List<String> intrests) {
        this.intrests = intrests;
    }
    public boolean addIntrest(String intrest){
        return getIntrests().add(intrest);
    }
    public boolean removeIntrest(String intrest){
        return getIntrests().remove(intrest);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" +
                password + ", intrests=" +
                getIntrests() + '}';
    }
}
