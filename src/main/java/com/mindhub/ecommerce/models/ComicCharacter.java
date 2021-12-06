package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ComicCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comic_id")
    private Comic comic;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "character_id")
    private Character character;

    public ComicCharacter(){

    }

    public ComicCharacter(Comic comic, Character character){
        this.comic = comic;
        this.character = character;
        comic.addProtagonist(this);
        character.addAppearance(this);
    }

    public Long getId() {
        return id;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComicCharacter{");
        sb.append("id=").append(id);
        sb.append(", comic=").append(comic);
        sb.append(", character=").append(character);
        sb.append('}');
        return sb.toString();
    }
}
