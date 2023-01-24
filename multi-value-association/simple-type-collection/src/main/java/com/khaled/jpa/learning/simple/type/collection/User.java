package com.khaled.jpa.learning.simple.type.collection;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author khaled
 */
@Entity
@Table(name = "usr_t")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id private Long id;
    private String email;
    private String password;
    //lazy by defaults
    @ElementCollection
     Set<String> intrests= new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof User u)&&
                Objects.equals(this.id, u.id);
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

    public Set<String> getIntrests() {
        return intrests;
    }

    public void setIntrests(Set<String> intrests) {
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
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" +
                password + ", intrests=" +
                getIntrests() + '}';
    }
}
