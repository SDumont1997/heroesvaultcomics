package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Author;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String birthPlace;
    private Set<ComicDTO> works;
    private Set<CharacterDTO> inventedCharacters;

    public AuthorDTO(){

    }

    public AuthorDTO(Author author){
        this.id = author.getId();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.birthDate = author.getBirthDate();
        this.birthPlace = author.getBirthPlace();
        this.works = author.getWorks().stream().map(ComicDTO::new).collect(toSet());
        this.inventedCharacters = author.getInventedCharacters().stream().map(CharacterDTO::new).collect(toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Set<ComicDTO> getWorks() {
        return works;
    }

    public void setWorks(Set<ComicDTO> works) {
        this.works = works;
    }

    public Set<CharacterDTO> getInventedCharacters() {
        return inventedCharacters;
    }

    public void setInventedCharacters(Set<CharacterDTO> inventedCharacters) {
        this.inventedCharacters = inventedCharacters;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorDTO{");
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
