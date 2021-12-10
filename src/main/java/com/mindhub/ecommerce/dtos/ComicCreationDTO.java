package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.models.Publisher;

import java.time.LocalDate;

public class ComicCreationDTO {
    private String title;
    private Author author;
    private LocalDate publicationDate;
    private Publisher publisher;
    private Double price;
    private Integer stock;

    public ComicCreationDTO() {
    }

    public ComicCreationDTO(String title, Author author, LocalDate publicationDate, Publisher publisher, Double price, Integer stock) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicCreationDTO{");
        sb.append("title='").append(title).append('\'');
        sb.append("author='").append(author).append('\'');
        sb.append("publicationDate='").append(publicationDate).append('\'');
        sb.append("publisher='").append(publisher).append('\'');
        sb.append(", price='").append(price);
        sb.append(", stock=").append(stock).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
