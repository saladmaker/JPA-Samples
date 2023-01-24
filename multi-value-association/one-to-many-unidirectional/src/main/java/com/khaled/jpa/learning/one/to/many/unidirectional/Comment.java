package com.khaled.jpa.learning.one.to.many.unidirectional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Comment {
    @GeneratedValue
    @Id private Long id;
    private String content;

    public Comment() {
    }

    public Comment(String conten) {
        this.content = conten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String conten) {
        this.content = conten;
    }
    
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Comment c)&&
                Objects.equals(id, c.id);               
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", conten=" + content + '}';
    }
    
}
