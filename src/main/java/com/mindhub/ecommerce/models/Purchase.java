package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
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
    @ManyToMany(mappedBy = "purchases", fetch = FetchType.EAGER)
    private Set<Comic> comics = new HashSet<>();
    @ManyToMany(mappedBy = "purchases", fetch = FetchType.EAGER)
    private Set<Merch> merch = new HashSet<>();
    private Double amount;
    private PaymentOption paymentOption;
    private String cardNumber;
    private Integer cardCvv;

    public Purchase() {

    }

    public Purchase(AppUser user, Set<Comic> comics, Set<Merch> merch, Double amount, PaymentOption paymentOption) {
        this.user = user;
        this.comics = comics;
        this.merch = merch;
        this.amount = amount;
        this.paymentOption = paymentOption;
        comics.forEach(comic -> comic.addPurchase(this));
        merch.forEach(merch1 -> merch1.addPurchase(this));
    }

    public Purchase(AppUser user, Set<Comic> comics, Set<Merch> merch, Double amount, PaymentOption paymentOption, String cardNumber, Integer cardCvv){
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

    public Set<Comic> getComics() {
        return comics;
    }

    public void setComics(Set<Comic> comics) {
        this.comics = comics;
        comics.forEach(comic -> comic.addPurchase(this));
    }

    public void addComics(Comic comic){
        this.comics.add(comic);
        comic.addPurchase(this);
    }

    public Set<Merch> getMerch() {
        return merch;
    }

    public void setMerch(Set<Merch> merch) {
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
