package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.PaymentOption;
import com.mindhub.ecommerce.models.Purchase;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class PurchaseDTO {
    private Long id;
    private String username;
    private Set<ComicDTO> comics;
    private Set<MerchDTO> merch;
    private Double amount;
    private PaymentOption paymentOption;
    private String cardNumber;

    public PurchaseDTO(){

    }

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.username = purchase.getUser().getUsername();
        this.comics = purchase.getComics().stream().map(ComicDTO::new).collect(toSet());
        this.merch = purchase.getMerch().stream().map(MerchDTO::new).collect(toSet());
        this.amount = purchase.getAmount();
        this.paymentOption = purchase.getPaymentOption();
        this.cardNumber = purchase.getCardNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<ComicDTO> getComics() {
        return comics;
    }

    public void setComics(Set<ComicDTO> comics) {
        this.comics = comics;
    }

    public Set<MerchDTO> getMerch() {
        return merch;
    }

    public void setMerch(Set<MerchDTO> merch) {
        this.merch = merch;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentOption getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PurchaseDTO{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", comics=").append(comics);
        sb.append(", merch=").append(merch);
        sb.append(", amount=").append(amount);
        sb.append(", paymentOption=").append(paymentOption);
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
