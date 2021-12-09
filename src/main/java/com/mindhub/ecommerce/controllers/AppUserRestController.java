package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.AppUserDTO;
import com.mindhub.ecommerce.dtos.CharacterCreationDTO;
import com.mindhub.ecommerce.models.AppUser;
import com.mindhub.ecommerce.services.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AppUserRestController {

    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/appUsers")
    public List<AppUserDTO> getAppUsers() {
        return appUserService.getAll().stream().map(appUser -> new AppUserDTO(appUser)).collect(toList());
    }
    @GetMapping("/appUsers/{id}")
    public AppUserDTO getAppUser(@PathVariable Long id) {
        return new AppUserDTO(appUserService.getById(id));
    }

    @PostMapping("/appUsers")
    public ResponseEntity<Object> register (Authentication authentication, @RequestBody AppUserDTO appUserDTO ){
        String username = appUserDTO.getUsername();
        String email = appUserDTO.getEmail();
        String password = appUserDTO.getPassword();
        String firstName = appUserDTO.getFirstName();
        String lastName = appUserDTO.getLastName();
        LocalDate birthDate = appUserDTO.getBirthDate();
        //boolean isAdmin = false;
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (appUserService.getByUsername(username) != null) {
            return new ResponseEntity<>("Username already in use", HttpStatus.FORBIDDEN);
        }
        if (!IsValidAge(birthDate)){
            return new ResponseEntity<>("User must be older than fourteen years old", HttpStatus.FORBIDDEN);
        }
        appUserService.save(new AppUser(username, email, passwordEncoder.encode(password), firstName, lastName, birthDate, false));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public boolean IsValidAge (LocalDate birthDate){
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);
        return period.getYears() > 13;
    }

    @GetMapping("/appUsers/current")
    public AppUserDTO getCurrent(Authentication authentication) {
        return new AppUserDTO(appUserService.getByEmail(authentication.getName()));
    }

}
