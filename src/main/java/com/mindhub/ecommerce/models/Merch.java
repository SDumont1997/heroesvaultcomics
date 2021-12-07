package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Merch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private MerchType merchType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "character_id")
    private Character character;
    private Double price;
    private Integer stock;

    public Merch() {

    }

    public Merch(MerchType merchType, Character character, Double price, Integer stock) {
        this.merchType = merchType;
        this.character = character;
        this.price = price;
        this.stock = stock;
        character.addMerch(this);
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Merch{");
        sb.append("id=").append(id);
        sb.append(", merchType=").append(merchType);
        sb.append(", character=").append(character);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append('}');
        return sb.toString();
    }
}
