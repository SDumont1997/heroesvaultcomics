package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.AppUser;

import java.util.List;
import java.util.Set;

public class PurchaseCreationDTO {
    private AppUser appUser;
    private Set<Long> comicIds;
    private Set<Long> merchIds;
    private Double amount;
    private String paymentOption;
    private String cardNumber;
    private Integer cardCvv;

    public PurchaseCreationDTO(){

    }

    public PurchaseCreationDTO(AppUser appUser, Set<Long> comicIds, Set<Long> merchIds, Double amount, String paymentOption, String cardNumber, Integer cardCvv){
        this.appUser = appUser;
        this.comicIds = comicIds;
        this.merchIds = merchIds;
        this.amount = amount;
        this.paymentOption = paymentOption;
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<Long> getComicIds() {
        return comicIds;
    }

    public void setComicIds(Set<Long> comicIds) {
        this.comicIds = comicIds;
    }

    public Set<Long> getMerchIds() {
        return merchIds;
    }

    public void setMerchIds(Set<Long> merchIds) {
        this.merchIds = merchIds;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardCvv() {
        return cardCvv;
    }

    public void setCardCvv(Integer cardCvv) {
        this.cardCvv = cardCvv;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PurchaseCreationDTO{");
        sb.append("appUser=").append(appUser);
        sb.append(", comicIds=").append(comicIds);
        sb.append(", merchIds=").append(merchIds);
        sb.append(", amount=").append(amount);
        sb.append(", paymentOption='").append(paymentOption).append('\'');
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", cardCvv=").append(cardCvv);
        sb.append('}');
        return sb.toString();
    }
}
