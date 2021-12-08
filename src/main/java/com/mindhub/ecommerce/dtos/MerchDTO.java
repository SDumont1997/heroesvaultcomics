package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Merch;
import com.mindhub.ecommerce.models.MerchType;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class MerchDTO {
    private Long id;
    private String name;
    private MerchType merchType;
    private String characterName;
    private Double price;
    private Integer stock;
    private String imgUrl;
    private Set<PurchaseDTO> purchases;

    public MerchDTO(){

    }

    public MerchDTO(Merch merch) {
        this.id = merch.getId();
        this.name = merch.getName();
        this.merchType = merch.getMerchType();
        this.characterName = merch.getCharacter().getAlias();
        this.price = merch.getPrice();
        this.stock = merch.getStock();
        this.imgUrl = merch.getImgUrl();
        this.purchases = merch.getPurchases().stream().map(PurchaseDTO::new).collect(toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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

    public Set<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MerchDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", merchType=").append(merchType);
        sb.append(", characterName='").append(characterName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", imgUrl='").append(imgUrl).append('\'');
        sb.append(", purchases=").append(purchases);
        sb.append('}');
        return sb.toString();
    }
}
