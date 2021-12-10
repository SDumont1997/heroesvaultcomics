package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.MerchCreationDTO;
import com.mindhub.ecommerce.dtos.MerchDTO;
import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.models.Merch;
import com.mindhub.ecommerce.models.MerchType;
import com.mindhub.ecommerce.services.IMerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class MerchRestController {
    @Autowired
    private IMerchService merchService;

    @GetMapping("/merchs")
    public Set<MerchDTO> getMerchs(){
        return merchService.getAll().stream().map(MerchDTO::new).collect(toSet());
    }

    @GetMapping("/merchs/{id}")
    public MerchDTO getMerchById(@PathVariable Long id){
        return new MerchDTO(merchService.getById(id));
    }

    @PostMapping("/merchs")
    public ResponseEntity<Object> createMerch(Authentication authentication, @RequestBody MerchCreationDTO merchCreationDTO){
        String name = merchCreationDTO.getName();
        MerchType merchType = merchCreationDTO.getMerchType();
        Character character = merchCreationDTO.getCharacter();
        Double price = merchCreationDTO.getPrice();
        Integer stock = merchCreationDTO.getStock();
        if (name.isEmpty() || merchType == null || character == null || price <= 0 || stock <= 0){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        Merch newMerch = new Merch(name, merchType, character, price, stock);
        merchService.save(newMerch);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
