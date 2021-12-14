package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.CharacterCreationDTO;
import com.mindhub.ecommerce.dtos.CharacterDTO;
import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.models.Publisher;
import com.mindhub.ecommerce.services.IAppUserService;
import com.mindhub.ecommerce.services.IAuthorService;
import com.mindhub.ecommerce.services.ICharacterService;
import com.mindhub.ecommerce.services.IPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static java.util.stream.Collectors.toSet;


@RestController
@RequestMapping("/api")
public class CharacterRestController {

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private ICharacterService characterService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private IPublisherService publisherService;

    @GetMapping("/characters")
    public Set<CharacterDTO> getCharacters(){
        return characterService.getAll().stream().map(CharacterDTO::new).collect(toSet());
    }

    @GetMapping("/characters/{id}")
    public CharacterDTO getCharacterById(@PathVariable Long id){
        return new CharacterDTO(characterService.getById(id));
    }

    @PostMapping("/characters")
    public ResponseEntity<Object> createCharacter(Authentication authentication, @RequestBody CharacterCreationDTO characterCreationDTO){
        String characterName = characterCreationDTO.getName();
        String characterAlias = characterCreationDTO.getAlias();
        String characterFirstAppearance = characterCreationDTO.getFirstAppearance();
        String characterBirthPlace = characterCreationDTO.getBirthPlace();
        Author characterCreator = authorService.getById(characterCreationDTO.getAuthorId());
        Publisher characterPublisher = publisherService.getById(characterCreationDTO.getPublisherId());
        String imgUrl = characterCreationDTO.getImgUrl();
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.FORBIDDEN);
        }
        if (characterName.isEmpty() || characterAlias.isEmpty() || characterFirstAppearance.isEmpty() || characterBirthPlace.isEmpty() || characterCreationDTO.getAuthorId() == null || characterCreationDTO.getPublisherId() == null){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        if (characterCreator == null || characterPublisher == null){
            return new ResponseEntity<>("Invalid author or publisher id", HttpStatus.BAD_REQUEST);
        }
        Character newCharacter = new Character(characterName, characterAlias, characterFirstAppearance, characterBirthPlace, characterCreator, characterPublisher);
        newCharacter.setImgUrl(imgUrl);
        characterService.save(newCharacter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
