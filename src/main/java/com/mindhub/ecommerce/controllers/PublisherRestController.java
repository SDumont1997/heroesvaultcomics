package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.PublisherCreationDTO;
import com.mindhub.ecommerce.dtos.PublisherDTO;
import com.mindhub.ecommerce.models.Publisher;
import com.mindhub.ecommerce.services.IAppUserService;
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
public class PublisherRestController {

    @Autowired
    IPublisherService publisherService;

    @Autowired
    IAppUserService appUserService;

    @GetMapping("/publishers")
    public Set<PublisherDTO> getPublishers(){
        return publisherService.getAll().stream().map(PublisherDTO::new).collect(toSet());
    }

    @GetMapping("publishers/{id}")
    public PublisherDTO getPublisherById(@PathVariable Long id){
        return new PublisherDTO(publisherService.getById(id));
    }

    @PostMapping("/publishers")
    public ResponseEntity<Object> createPublisher(Authentication authentication, @RequestBody PublisherCreationDTO publisherCreationDTO){
        String publisherName = publisherCreationDTO.getName();
        LocalDate creationDate = publisherCreationDTO.getCreationDate();
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.FORBIDDEN);
        }
        if (publisherName.isEmpty() || creationDate == null){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        Publisher newPublisher = new Publisher(publisherName, creationDate);
        publisherService.save(newPublisher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
