package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.ComicCreationDTO;
import com.mindhub.ecommerce.dtos.ComicDTO;
import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.models.Comic;
import com.mindhub.ecommerce.models.Publisher;
import com.mindhub.ecommerce.services.IComicService;
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
        Author author = comicCreationDTO.getAuthor();
        LocalDate publicationDate = comicCreationDTO.getPublicationDate();
        Publisher publisher = comicCreationDTO.getPublisher();
        Double price = comicCreationDTO.getPrice();
        Integer stock = comicCreationDTO.getStock();
        if (title.isEmpty() || author == null || publicationDate == null || publisher == null || price <= 0 || stock < 0){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        Comic newComic = new Comic(title, author, publicationDate, publisher, price, stock);
        comicService.save(newComic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
