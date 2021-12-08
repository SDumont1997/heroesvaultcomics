package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Comic;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ComicDTO {
    private Long id;
    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private String publisherName;
    private Set<CharacterDTO> protagonists;
    private Double price;
    private Integer stock;
    private String coverImgUrl;
    private Set<PurchaseDTO> purchases;

    public ComicDTO(){

    }

    public ComicDTO(Comic comic){
        this.id = comic.getId();
        this.title = comic.getTitle();
        this.authorName = comic.getAuthor().getFirstName() + " " + comic.getAuthor().getLastName();
        this.publicationDate = comic.getPublicationDate();
        this.publisherName = comic.getPublisher().getName();
        this.protagonists = comic.getProtagonists().stream().map(CharacterDTO::new).collect(toSet());
        this.price = comic.getPrice();
        this.stock = comic.getStock();
        this.coverImgUrl = comic.getCoverImgUrl();
        this.purchases = comic.getPurchases().stream().map(PurchaseDTO::new).collect(toSet());
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Set<CharacterDTO> getProtagonists() {
        return protagonists;
    }

    public void setProtagonists(Set<CharacterDTO> protagonists) {
        this.protagonists = protagonists;
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

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Set<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicDTO{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", publisherName='").append(publisherName).append('\'');
        sb.append(", protagonists=").append(protagonists);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", coverImgUrl='").append(coverImgUrl).append('\'');
        sb.append(", purchases=").append(purchases);
        sb.append('}');
        return sb.toString();
    }
}
