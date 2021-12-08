package com.mindhub.ecommerce.dtos;

import java.util.Set;
import com.mindhub.ecommerce.models.Character;

import static java.util.stream.Collectors.toSet;

public class CharacterDTO {
    private Long id;
    private String name;
    private String alias;
    private String firstAppearance;
    private String birthPlace;
    private String creatorName;
    private String publisherName;
    private Set<ComicDTO> appearances;
    private Set<MerchDTO> merch;
    private String imgUrl;

    public CharacterDTO() {

    }

    public CharacterDTO(Character character){
        this.id = character.getId();
        this.name = character.getName();
        this.alias = character.getAlias();
        this.firstAppearance = character.getFirstAppearance();
        this.birthPlace = character.getBirthPlace();
        this.creatorName = character.getCreator().getFirstName() + " " + character.getCreator().getLastName();
        this.publisherName = character.getPublisher().getName();
        this.appearances = character.getAppearances().stream().map(ComicDTO::new).collect(toSet());
        this.merch = character.getMerch().stream().map(MerchDTO::new).collect(toSet());
        this.imgUrl = character.getImgUrl();
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Set<ComicDTO> getAppearances() {
        return appearances;
    }

    public void setAppearances(Set<ComicDTO> appearances) {
        this.appearances = appearances;
    }

    public Set<MerchDTO> getMerch() {
        return merch;
    }

    public void setMerch(Set<MerchDTO> merch) {
        this.merch = merch;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharacterDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", firstAppearance='").append(firstAppearance).append('\'');
        sb.append(", birthPlace='").append(birthPlace).append('\'');
        sb.append(", creatorName='").append(creatorName).append('\'');
        sb.append(", publisherName='").append(publisherName).append('\'');
        sb.append(", appearances=").append(appearances);
        sb.append(", merch=").append(merch);
        sb.append(", imgUrl='").append(imgUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
