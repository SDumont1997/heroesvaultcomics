package com.mindhub.ecommerce.dtos;

import java.time.LocalDate;

public class AuthorCreationDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String birthPlace;

    public AuthorCreationDTO(){

    }

    public AuthorCreationDTO(String firstName, String lastName, LocalDate birthDate, String birthPlace){
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

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthorCreationDTO{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", birthPlace='").append(birthPlace).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
