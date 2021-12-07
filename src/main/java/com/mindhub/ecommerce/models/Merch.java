package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Merch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private MerchType merchType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "character_id")
    private Character character;
    private Double price;
    private Integer stock;
    private String imgUrl;
    @ManyToMany(mappedBy = "merch", fetch = FetchType.EAGER)
    private Set<Purchase> purchases = new HashSet<>();

    public Merch() {

    }

    public Merch(String name, MerchType merchType, Character character, Double price, Integer stock) {
        this.name = name;
        this.merchType = merchType;
        this.character = character;
        this.price = price;
        this.stock = stock;
        character.addMerch(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MerchType getMerchType() {
        return merchType;
    }

    public void setMerchType(MerchType merchType) {
        this.merchType = merchType;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Merch{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", merchType=").append(merchType);
        sb.append(", character=").append(character);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", purchases").append(purchases);
        sb.append('}');
        return sb.toString();
    }
}
