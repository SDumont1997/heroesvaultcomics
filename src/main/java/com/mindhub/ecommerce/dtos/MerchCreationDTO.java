package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.models.MerchType;

public class MerchCreationDTO {
    private String name;
    private MerchType merchType;
    private Long characterId;
    private Double price;
    private Integer stock;

    public MerchCreationDTO() {
    }

    public MerchCreationDTO(String name, MerchType merchType, Long characterId, Double price, Integer stock) {
        this.name = name;
        this.merchType = merchType;
        this.characterId = characterId;
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

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacter(Long characterId) {
        this.characterId = characterId;
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
        final StringBuilder sb = new StringBuilder("MerchCreationDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", merchType=").append(merchType);
        sb.append(", characterId=").append(characterId);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append('}');
        return sb.toString();
    }
}
