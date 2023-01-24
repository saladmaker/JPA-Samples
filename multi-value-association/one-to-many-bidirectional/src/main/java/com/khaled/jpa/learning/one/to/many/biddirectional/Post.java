package com.khaled.jpa.learning.one.to.many.biddirectional;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
public class Post {

    @GeneratedValue
    @Id
    private Long id;
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private char[] content;
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    // Without this annotation the jpa provider will create a join table
    //@JoinColumn(name = "POST_ID")
    private List<Comment> comments  = new ArrayList<>();

    public Post() {
    }

    public Post(String title) {
        this.title = title;
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

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comment) {
        this.comments = comment;
    }

    public boolean addComment(Comment comment) {
        return getComments().add(comment);
    }

    public boolean removeComment(Comment comment) {
        if (getComments().contains(comment)) {
            return getComments().remove(comment);
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Post p)
                && Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
