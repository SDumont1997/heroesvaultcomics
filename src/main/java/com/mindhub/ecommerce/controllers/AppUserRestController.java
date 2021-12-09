package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.AppUserCreationDTO;
import com.mindhub.ecommerce.dtos.AppUserDTO;
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
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class AppUserRestController {

    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/appUsers")
    public Set<AppUserDTO> getAppUsers() {
        return appUserService.getAll().stream().map(AppUserDTO::new).collect(toSet());
    }
    @GetMapping("/appUsers/{id}")
    public AppUserDTO getAppUser(@PathVariable Long id) {
        return new AppUserDTO(appUserService.getById(id));
    }

    @PostMapping("/appUsers")
    public ResponseEntity<Object> createAppUser (Authentication authentication, @RequestBody AppUserCreationDTO appUserCreationDTO ){
        String username = appUserCreationDTO.getUsername();
        String email = appUserCreationDTO.getEmail();
        String password = appUserCreationDTO.getPassword();
        String firstName = appUserCreationDTO.getFirstName();
        String lastName = appUserCreationDTO.getLastName();
        LocalDate birthDate = appUserCreationDTO.getBirthDate();
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || birthDate == null) {
            return new ResponseEntity<>("Missing parameters", HttpStatus.FORBIDDEN);
        }
        if (appUserService.getByUsername(username) != null) {
            return new ResponseEntity<>("Username already in use", HttpStatus.FORBIDDEN);
        }
        if (!IsValidAge(birthDate)){
            return new ResponseEntity<>("User must be at least fourteen years old", HttpStatus.FORBIDDEN);
        }
        AppUser newAppUser = new AppUser(username, email, passwordEncoder.encode(password), firstName, lastName, birthDate, false);
        appUserService.save(newAppUser);
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

    @PostMapping("/appUsers/setAdmin")
    public ResponseEntity<Object> setAdmin(Authentication authentication, @RequestParam Long id){
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.UNAUTHORIZED);
        }
        if (appUserService.getById(id) == null){
            return new ResponseEntity<>("Target user does not exist", HttpStatus.FORBIDDEN);
        }
        if (appUserService.getById(id).isAdmin()){
            return new ResponseEntity<>("User already has administrator privileges", HttpStatus.ALREADY_REPORTED);
        }
        AppUser user = appUserService.getById(id);
        user.setAdmin(true);
        appUserService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/appUsers/revokeAdmin")
    public ResponseEntity<Object> revokeAdmin(Authentication authentication, @RequestParam Long id){
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.UNAUTHORIZED);
        }
        if (appUserService.getById(id) == null){
            return new ResponseEntity<>("Target user does not exist", HttpStatus.FORBIDDEN);
        }
        if (!appUserService.getById(id).isAdmin()){
            return new ResponseEntity<>("User does not have administrator privileges", HttpStatus.ALREADY_REPORTED);
        }
        AppUser user = appUserService.getById(id);
        user.setAdmin(false);
        appUserService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
