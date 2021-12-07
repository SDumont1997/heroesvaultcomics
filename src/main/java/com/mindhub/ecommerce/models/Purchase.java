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
    private User user;
    @ManyToMany(mappedBy = "purchases", fetch = FetchType.EAGER)
    private Set<Comic> comics = new HashSet<>();
    @ManyToMany(mappedBy = "purchases", fetch = FetchType.EAGER)
    private Set<Merch> merch = new HashSet<>();
    private Double amount;
    private PaymentOption paymentOption;
    private String cardNumber;

}
