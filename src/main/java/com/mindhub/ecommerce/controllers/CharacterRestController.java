package com.mindhub.ecommerce.controllers;


import com.mindhub.ecommerce.dtos.CharacterDTO;
import com.mindhub.ecommerce.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static java.util.stream.Collectors.toSet;


@RestController
@RequestMapping("/api")
public class CharacterRestController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/characters")
    public Set<CharacterDTO> getCharacters(){
        return characterService.getAll().stream().map(CharacterDTO::new).collect(toSet());
    }
}
