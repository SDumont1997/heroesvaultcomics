package com.mindhub.ecommerce.models;

import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
    private LocalDate publicationDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @OneToMany(mappedBy = "comic", fetch = FetchType.EAGER)
    private Set<ComicCharacter> protagonists = new HashSet<>();

    public Comic(){

    }

    public Comic(String title, Author author, LocalDate publicationDate, Publisher publisher){
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<ComicCharacter> getProtagonists() {
        return protagonists;
    }

    public void addProtagonist(ComicCharacter comicCharacter) {
        this.protagonists.add(comicCharacter);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comic{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author=").append(author);
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", publisher=").append(publisher);
        sb.append(", protagonists=").append(protagonists);
        sb.append('}');
        return sb.toString();
    }
}
