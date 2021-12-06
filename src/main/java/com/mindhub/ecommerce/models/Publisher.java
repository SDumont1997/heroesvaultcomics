package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private LocalDate creationDate;
    @OneToMany(mappedBy = "publisher" ,fetch = FetchType.EAGER)
    private Set<Comic> publications = new HashSet<>();
}
