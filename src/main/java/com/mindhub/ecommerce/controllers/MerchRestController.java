package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.MerchCreationDTO;
import com.mindhub.ecommerce.dtos.MerchDTO;
import com.mindhub.ecommerce.models.Character;
import com.mindhub.ecommerce.models.Comic;
import com.mindhub.ecommerce.models.Merch;
import com.mindhub.ecommerce.models.MerchType;
import com.mindhub.ecommerce.services.IAppUserService;
import com.mindhub.ecommerce.services.ICharacterService;
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

    @Autowired
    private ICharacterService characterService;

    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/merch")
    public Set<MerchDTO> getMerch(){
        return merchService.getAll().stream().map(MerchDTO::new).collect(toSet());
    }

    @GetMapping("/merch/{id}")
    public MerchDTO getMerchById(@PathVariable Long id){
        return new MerchDTO(merchService.getById(id));
    }

    @PostMapping("/merch")
    public ResponseEntity<Object> createMerch(Authentication authentication, @RequestBody MerchCreationDTO merchCreationDTO){
        String name = merchCreationDTO.getName();
        MerchType merchType = merchCreationDTO.getMerchType();
        Character character = characterService.getById(merchCreationDTO.getCharacterId());
        Double price = merchCreationDTO.getPrice();
        Integer stock = merchCreationDTO.getStock();
        String merchImgUrl = merchCreationDTO.getImgUrl();
        if (!appUserService.getByEmail(authentication.getName()).isAdmin()){
            return new ResponseEntity<>("User does not have authorization for this action", HttpStatus.UNAUTHORIZED);
        }
        if (name.isEmpty() || merchType == null || price <= 0 || stock <= 0){
            return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
        }
        if (character == null ){
            return new ResponseEntity<>("Invalid character id", HttpStatus.BAD_REQUEST);
        }
        Merch newMerch = new Merch(name, merchType, character, price, stock);
        newMerch.setImgUrl(merchImgUrl);
        merchService.save(newMerch);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/merch/update")
    public ResponseEntity<Object> updateComic(Authentication authentication, @RequestParam Long id, @RequestParam Double price, @RequestParam Integer stock){
        Merch merchItem = merchService.getById(id);
        if (price != 0 && !price.toString().equals("")){
            merchItem.setPrice(price);
        }
        if (stock != 0 && !stock.toString().equals("")){
            merchItem.setStock(stock);
        }
        merchService.save(merchItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
