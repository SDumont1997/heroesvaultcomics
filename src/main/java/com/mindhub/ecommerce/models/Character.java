package com.mindhub.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private String alias;
    private String firstAppearance;
    private String birthPlace;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private Author creator;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER)
    private Set<ComicCharacter> appearances = new HashSet<>();
    @OneToMany(mappedBy = "character", fetch = FetchType.EAGER)
    private Set<Merch> merch = new HashSet<>();
    private String imgUrl;

    public Character(){

    }
    ;
    public Character(String name, String alias, String firstAppearance, String birthPlace, Author creator, Publisher publisher){
        this.name = name;
        this.alias = alias;
        this.firstAppearance = firstAppearance;
        this.birthPlace = birthPlace;
        this.creator = creator;
        creator.addInventedCharacter(this);
        this.publisher = publisher;
        publisher.addCharacter(this);
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Author getCreator() {
        return creator;
    }

    public void setCreator(Author creator) {
        this.creator = creator;
        creator.addInventedCharacter(this);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        publisher.addCharacter(this);
    }

    public Set<ComicCharacter> getAppearances() {
        return appearances;
    }

    public void addAppearance(ComicCharacter comicCharacter) {
        this.appearances.add(comicCharacter);
    }

    public Set<Merch> getMerch() {
        return merch;
    }

    public void addMerch(Merch merch) {
        this.merch.add(merch);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Character{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", firstAppearance='").append(firstAppearance).append('\'');
        sb.append(", birthPlace='").append(birthPlace).append('\'');
        sb.append(", creator=").append(creator);
        sb.append(", publisher=").append(publisher);
        sb.append(", appearances=").append(appearances);
        sb.append(", merch=").append(merch);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append('}');
        return sb.toString();
    }
}
