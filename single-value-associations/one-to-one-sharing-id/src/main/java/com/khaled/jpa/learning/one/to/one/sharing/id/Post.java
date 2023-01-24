package com.khaled.jpa.learning.one.to.one.sharing.id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity

public class Post {
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Id private Long id;
    private String title;
    @OneToOne(mappedBy = "post",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private PostDetail detail;
    public Post(String title) {
        this.title = title;
    }
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Post p)&&
                Objects.equals(p.id, this.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostDetail getDetail() {
        return detail;
    }

    public void setDetail(PostDetail detail) {
        this.detail = detail;
    }
    

    
    public Post() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    
    
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title +'}';
    }
    
}
