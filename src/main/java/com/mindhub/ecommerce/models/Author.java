package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String birthPlace;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Comic> works = new HashSet<>();
    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
    private Set<Character> inventedCharacters = new HashSet<>();

    public Author(){

    }

    public Author(String firstName, String lastName, LocalDate birthDate, String birthPlace){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace(){
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Set<Comic> getWorks() {
        return works;
    }

    public void addWork(Comic comic) {
        this.works.add(comic);
    }

    public Set<Character> getInventedCharacters() {
        return inventedCharacters;
    }

    public void addInventedCharacter(Character character){
        this.inventedCharacters.add(character);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", birthPlace='").append(birthPlace).append('\'');
        sb.append(", works=").append(works);
        sb.append(", inventedCharacters=").append(inventedCharacters);
        sb.append('}');
        return sb.toString();
    }
}
