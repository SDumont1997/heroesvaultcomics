package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser user;
    @ManyToMany(mappedBy = "purchases")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Comic> comics = new ArrayList<>();
    @ManyToMany(mappedBy = "purchases")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Merch> merch = new ArrayList<>();
    private Double amount;
    private PaymentOption paymentOption;
    private String cardNumber;
    private Integer cardCvv;

    public Purchase() {

    }

    public Purchase(AppUser user, List<Comic> comics, List<Merch> merch, Double amount, PaymentOption paymentOption) {
        this.user = user;
        this.comics = comics;
        this.merch = merch;
        this.amount = amount;
        this.paymentOption = paymentOption;
        comics.forEach(comic -> comic.addPurchase(this));
        merch.forEach(merch1 -> merch1.addPurchase(this));
    }

    public Purchase(AppUser user, List<Comic> comics, List<Merch> merch, Double amount, PaymentOption paymentOption, String cardNumber, Integer cardCvv){
        this.user = user;
        this.comics = comics;
        this.merch = merch;
        this.amount = amount;
        this.paymentOption = paymentOption;
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        comics.forEach(comic -> comic.addPurchase(this));
        merch.forEach(merch1 -> merch1.addPurchase(this));
    }

    public Long getId() {
        return id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
        comics.forEach(comic -> comic.addPurchase(this));
    }

    public void addComics(Comic comic){
        this.comics.add(comic);
        comic.addPurchase(this);
    }

    public List<Merch> getMerch() {
        return merch;
    }

    public void setMerch(List<Merch> merch) {
        this.merch = merch;
        merch.forEach(merch1 -> merch1.addPurchase(this));
    }

    public void addMerch(Merch merch){
        this.merch.add(merch);
        merch.addPurchase(this);
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

    public Integer getCardCvv(){
        return this.cardCvv;
    }

    public void setCardCvv(Integer cardCvv) {
        this.cardCvv = cardCvv;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Purchase{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", comics=").append(comics);
        sb.append(", merch=").append(merch);
        sb.append(", amount=").append(amount);
        sb.append(", paymentOption=").append(paymentOption);
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", cardCvv=").append(cardCvv);
        sb.append('}');
        return sb.toString();
    }
}
