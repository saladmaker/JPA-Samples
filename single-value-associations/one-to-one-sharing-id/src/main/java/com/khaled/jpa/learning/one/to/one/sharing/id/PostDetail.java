package com.khaled.jpa.learning.one.to.one.sharing.id;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author khaled
 */
@Entity
class PostDetail {
    @Basic(fetch = FetchType.LAZY)
    @Lob()
    private char[] content;
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    private Post post;
    public PostDetail(char[] content) {
        this.content = content;
    }
    public PostDetail(){}
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PostDetail p)&&
                Objects.equals(getId(), p.getId());
    }

    public Long getId() {
        return getPost().getId();
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getPost().getId());
    }

    @Override
    public String toString() {
        return "PostDetails{" + "id=" + getPost().getId() + ", content=" + Arrays.toString(content) + '}';
    }    
}
