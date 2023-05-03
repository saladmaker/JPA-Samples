package com.khaled.jpa.learning.mappedsuperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

/**
 *
 * @author khaled
 */

@Entity
public class Post extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    private String content;

    public Post() {
    }

    public Post(String title, String content) {
        Objects.requireNonNull(title, "title must not be null");
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
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
                && Objects.equals(title, p.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
