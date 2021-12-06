package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private LocalDate creationDate;
    @OneToMany(mappedBy = "publisher" ,fetch = FetchType.EAGER)
    private Set<Comic> publications = new HashSet<>();
    @OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
    private Set<Character> characters = new HashSet<>();

    public Publisher(){

    }

    public Publisher(String name, LocalDate creationDate){
        this.name = name;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Comic> getPublications() {
        return publications;
    }

    public void addPublication(Comic publication) {
        this.publications.add(publication);
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Publisher{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", publications=").append(publications);
        sb.append(", characters=").append(characters);
        sb.append('}');
        return sb.toString();
    }
}
