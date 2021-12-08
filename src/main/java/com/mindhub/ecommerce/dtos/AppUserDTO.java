package com.mindhub.ecommerce.dtos;

import com.mindhub.ecommerce.models.AppUser;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AppUserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private boolean admin;
    private Set<PurchaseDTO> purchases;

    public AppUserDTO(){

    }

    public AppUserDTO(AppUser appUser){
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.password = appUser.getPassword();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.birthDate = appUser.getBirthDate();
        this.admin = appUser.isAdmin();
        this.purchases = appUser.getPurchases().stream().map(PurchaseDTO::new).collect(toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<PurchaseDTO> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<PurchaseDTO> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AppUserDTO{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", admin=").append(admin);
        sb.append(", purchases=").append(purchases);
        sb.append('}');
        return sb.toString();
    }
}
