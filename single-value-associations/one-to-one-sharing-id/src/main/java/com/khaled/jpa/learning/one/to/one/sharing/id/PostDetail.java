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
    @Lob
    private char[] content;

    @Id
    @OneToOne
    private Post post;

    public PostDetail(char[] content) {
        this.content = content;
        this.post = post;
    }
    protected PostDetail(){}

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        return (obj instanceof PostDetail pd)
            && Objects.equals(post, pd.post);
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
    void setPost(Post post){
        this.post = post;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(post);
    }

    @Override
    public String toString() {
        return "PostDetails{"  + "content=" + Arrays.toString(content) + '}';
    }    
}
