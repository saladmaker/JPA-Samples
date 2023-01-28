package com.khaled.jpa.learning.many.to.many.bidirectional;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author khaled
 */
@Entity
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String isbn;
    private String title;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "GENRE",
            joinColumns = @JoinColumn(name = "BOOK_ID")
    )
    @Column(name = "name")
    private List<String> genre;
    private int nbrofp;
    @ManyToMany(
            mappedBy = "books"
    )
    private Set<Author> authors = new HashSet<>();

    public Book() {
    }

    public Book(String isbn, String title, int nbrofp) {
        this.isbn = isbn;
        this.title = title;
        this.nbrofp = nbrofp;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Book b)
                && Objects.equals(b.getIsbn(), isbn);
    }

    public void addAuthor(Author author) {
        getAuthors().add(author);
    }

    public void removeAuthor(Author author) {
        getAuthors().remove(author);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public int getNbrofp() {
        return nbrofp;
    }

    public void setNbrofp(int nbrofp) {
        this.nbrofp = nbrofp;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.isbn);
        return hash;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", isbn=" + isbn + ", title=" + title + ", genre=" + genre + ", nbrofp=" + nbrofp + '}';
    }
}
