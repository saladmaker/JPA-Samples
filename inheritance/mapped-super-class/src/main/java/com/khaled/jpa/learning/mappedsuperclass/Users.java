package com.khaled.jpa.learning.mappedsuperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Users extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;
    private char[] password;

    public Users(String email, char[] password) {
        Objects.requireNonNull(email);
        this.email = email;
        this.password = password;
    }

    public Users() {
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Users u)
                && Objects.equals(this.email, u.email);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

}
