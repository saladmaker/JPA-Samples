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


    /*
    *
    *   fetch = FetchType.LAZY almost always a bad idea only if the other entity is huge
    *   orphan removal and cascade all (persist, merge, ...etc) help managing the state of entities
    */
    @OneToOne(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private PostDetail detail;

    public Post(String title) {
        this.title = title;
    }

    public Post() {
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        return (obj instanceof Post p)
                && null != id
                && Objects.equals(p.id, this.id);
    }

    public Long getId() {
        return id;
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

    public void addDetail(PostDetail detail) {
        this.detail = detail;
        detail.setPost(this);
    }
    
    public void removeDetail(){
        detail.setPost(null);
        detail = null;
    }
    

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    
    
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title +'}';
    }   
}
