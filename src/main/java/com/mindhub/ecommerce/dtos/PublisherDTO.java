package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.models.Comic;
import com.mindhub.ecommerce.models.Publisher;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class PublisherDTO {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private Set<ComicDTO> publications;
    private Set<CharacterDTO> characters;

    public PublisherDTO(){

    }

    public PublisherDTO(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.creationDate = publisher.getCreationDate();
        this.publications = publisher.getPublications().stream().map(ComicDTO::new).collect(toSet());
        this.characters = publisher.getCharacters().stream().map(CharacterDTO::new).collect(toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<ComicDTO> getPublications() {
        return publications;
    }

    public void setPublications(Set<ComicDTO> publications) {
        this.publications = publications;
    }

    public Set<CharacterDTO> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<CharacterDTO> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PublisherDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", publications=").append(publications);
        sb.append(", characters=").append(characters);
        sb.append('}');
        return sb.toString();
    }
}
