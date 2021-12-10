package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.ComicCreationDTO;
import com.mindhub.ecommerce.dtos.ComicDTO;
import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.models.Comic;
import com.mindhub.ecommerce.models.Publisher;
import com.mindhub.ecommerce.services.IAppUserService;
import com.mindhub.ecommerce.services.IAuthorService;
import com.mindhub.ecommerce.services.IComicService;
import com.mindhub.ecommerce.services.IPublisherService;
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
public class ComicRestController {

    @Autowired
    private IComicService comicService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IPublisherService publisherService;

    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/comics")
    public Set<ComicDTO> getComics(){
        return comicService.getAll().stream().map(ComicDTO::new).collect(toSet());
    }

    @GetMapping("/comics/{id}")
    public ComicDTO getComicById(@PathVariable Long id){
        return new ComicDTO(comicService.getById(id));
    }

    @PostMapping("/comics")
    public ResponseEntity<Object> createComic(Authentication authentication, @RequestBody ComicCreationDTO comicCreationDTO){
        String title = comicCreationDTO.getTitle();
        Author author = authorService.getById(comicCreationDTO.getAuthorId());
        LocalDate publicationDate = comicCreationDTO.getPublicationDate();
        Publisher publisher = publisherService.getById(comicCreationDTO.getPublisherId());
        Double price = comicCreationDTO.getPrice();
        Integer stock = comicCreationDTO.getStock();
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.UNAUTHORIZED);
        }
        if (title.isEmpty() || publicationDate == null || price <= 0 || stock < 0){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        if (author == null || publisher == null){
            return new ResponseEntity<>("Invalid author or publisher id", HttpStatus.BAD_REQUEST);
        }
        Comic newComic = new Comic(title, author, publicationDate, publisher, price, stock);
        comicService.save(newComic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
