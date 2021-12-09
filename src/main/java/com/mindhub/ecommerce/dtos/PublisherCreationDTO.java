package com.mindhub.ecommerce.dtos;

import java.time.LocalDate;

public class PublisherCreationDTO {
    private String name;
    private LocalDate creationDate;

    public PublisherCreationDTO(){

    }

    public PublisherCreationDTO(String name, LocalDate creationDate){
        this.name = name;
        this.creationDate = creationDate;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PublisherCreationDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append('}');
        return sb.toString();
    }
}
