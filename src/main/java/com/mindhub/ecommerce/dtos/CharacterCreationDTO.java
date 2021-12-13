package com.mindhub.ecommerce.dtos;

public class CharacterCreationDTO {
    private String name;
    private String alias;
    private String firstAppearance;
    private String birthPlace;
    private Long authorId;
    private Long publisherId;
    private String imgUrl;

    public CharacterCreationDTO() {

    }

    public CharacterCreationDTO(String name, String alias, String firstAppearance, String birthPlace, Long authorId, Long publisherId, String imgUrl) {
        this.name = name;
        this.alias = alias;
        this.firstAppearance = firstAppearance;
        this.birthPlace = birthPlace;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.imgUrl = imgUrl;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharacterCreationDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", alias='").append(alias).append('\'');
        sb.append(", firstAppearance='").append(firstAppearance).append('\'');
        sb.append(", birthPlace='").append(birthPlace).append('\'');
        sb.append(", authorId=").append(authorId);
        sb.append(", publisherId=").append(publisherId);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append('}');
        return sb.toString();
    }
}
