package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.models.Publisher;

import java.time.LocalDate;

public class ComicCreationDTO {
    private String title;
    private Long authorId;
    private LocalDate publicationDate;
    private Long publisherId;
    private Double price;
    private Integer stock;

    public ComicCreationDTO() {
    }

    public ComicCreationDTO(String title, Long authorId, LocalDate publicationDate, Long publisherId, Double price, Integer stock) {
        this.title = title;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
        this.publisherId = publisherId;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
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
        sb.append("authorId='").append(authorId).append('\'');
        sb.append("publicationDate='").append(publicationDate).append('\'');
        sb.append("publisherId='").append(publisherId).append('\'');
        sb.append(", price='").append(price);
        sb.append(", stock=").append(stock).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
