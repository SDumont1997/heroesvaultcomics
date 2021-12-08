package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.PaymentOption;
import com.mindhub.ecommerce.models.Purchase;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class PurchaseDTO {
    private Long id;
    private String username;
    private Set<String> comics;
    private Set<String> merch;
    private Double amount;
    private PaymentOption paymentOption;
    private String cardNumber;

    public PurchaseDTO(){

    }

    public PurchaseDTO(Purchase purchase) {
        this.id = purchase.getId();
        this.username = purchase.getUser().getUsername();
        this.comics = purchase.getComics().stream().map(comic -> comic.getTitle() + " - $" + comic.getPrice()).collect(toSet());
        this.merch = purchase.getMerch().stream().map(merch1 -> merch1.getName() + " - $" + merch1.getPrice()).collect(toSet());
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

    public Set<String> getComics() {
        return comics;
    }

    public void setComics(Set<String> comics) {
        this.comics = comics;
    }

    public Set<String> getMerch() {
        return merch;
    }

    public void setMerch(Set<String> merch) {
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
