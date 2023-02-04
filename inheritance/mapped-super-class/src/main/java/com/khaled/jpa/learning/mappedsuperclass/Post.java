package com.khaled.jpa.learning.mappedsuperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Post extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String title;
    private String content;

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Post p)
                && Objects.equals(this.title, p.title);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.title);
        return hash;
    }
}
