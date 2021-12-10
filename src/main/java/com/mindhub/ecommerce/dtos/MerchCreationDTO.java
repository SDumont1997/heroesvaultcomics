package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.models.MerchType;

public class MerchCreationDTO {
    private String name;
    private MerchType merchType;
    private Character character;
    private Double price;
    private Integer stock;

    public MerchCreationDTO() {
    }

    public MerchCreationDTO(String name, MerchType merchType, Character character, Double price, Integer stock) {
        this.name = name;
        this.merchType = merchType;
        this.character = character;
        this.price = price;
        this.stock = stock;
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

    @Override
    public String toString() {
        return "MerchCreationDTO{" +
                "name='" + name + '\'' +
                ", merchType=" + merchType +
                ", character=" + character +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
