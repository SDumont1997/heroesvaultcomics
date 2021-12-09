package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.AuthorCreationDTO;
import com.mindhub.ecommerce.dtos.AuthorDTO;
import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.services.IAppUserService;
import com.mindhub.ecommerce.services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class AuthorRestController {

    @Autowired
    IAuthorService authorService;

    @Autowired
    IAppUserService appUserService;

    @GetMapping("/authors")
    public Set<AuthorDTO> getAuthors(){
        return authorService.getAll().stream().map(AuthorDTO::new).collect(toSet());
    }

    @GetMapping("/authors/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id){
        return new AuthorDTO(authorService.getById(id));
    }

    @PostMapping("/authors")
    public ResponseEntity<Object> createAuthor(Authentication authentication, @RequestBody AuthorCreationDTO authorCreationDTO){
        String authorFirstName = authorCreationDTO.getFirstName();
        String authorLastName = authorCreationDTO.getLastName();
        LocalDate authorBirthDate = authorCreationDTO.getBirthDate();
        String authorBirthPlace = authorCreationDTO.getBirthPlace();
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.FORBIDDEN);
        }
        if (authorFirstName.isEmpty() || authorLastName.isEmpty() || authorBirthDate == null || authorBirthPlace.isEmpty()){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        Author newAuthor = new Author(authorFirstName, authorLastName, authorBirthDate, authorBirthPlace);
        authorService.save(newAuthor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
