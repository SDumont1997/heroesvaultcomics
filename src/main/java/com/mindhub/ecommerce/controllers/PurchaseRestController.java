package com.mindhub.ecommerce.controllers;

import com.mindhub.ecommerce.dtos.PurchaseCreationDTO;
import com.mindhub.ecommerce.dtos.PurchaseDTO;
import com.mindhub.ecommerce.models.*;
import com.mindhub.ecommerce.services.IAppUserService;
import com.mindhub.ecommerce.services.IComicService;
import com.mindhub.ecommerce.services.IMerchService;
import com.mindhub.ecommerce.services.IPurchaseService;
import com.mindhub.ecommerce.services.impl.PurchaseExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class PurchaseRestController {

    @Autowired
    public IAppUserService appUserService;

    @Autowired
    public IComicService comicService;

    @Autowired
    public IMerchService merchService;

    @Autowired
    public IPurchaseService purchaseService;

    @Autowired
    public PurchaseExporter exporter;

    @GetMapping("/purchases")
    public Set<PurchaseDTO> getPurchases(){
        return purchaseService.getAll().stream().map(PurchaseDTO::new).collect(toSet());
    }

    @GetMapping("/purchases/{id}")
    public PurchaseDTO getPurchaseById(@PathVariable Long id){
        return new PurchaseDTO(purchaseService.getById(id));
    }

    @Transactional
    @PostMapping("/purchases")
    public ResponseEntity<Object> createPurchase(Authentication authentication, @RequestBody PurchaseCreationDTO purchaseCreationDTO){
        AppUser appUser = appUserService.getByEmail(authentication.getName());
        Set<Comic> comics = new HashSet<>();
        purchaseCreationDTO.getComicIds().forEach(id -> comics.add(comicService.getById(id)));
        Set<Merch> merch = new HashSet<>();
        purchaseCreationDTO.getMerchIds().forEach(id -> merch.add(merchService.getById(id)));
        Double amountCheck = 0.00;
        for (Comic comic: comics){
            amountCheck += comic.getPrice();
        }
        for (Merch merch1: merch){
            amountCheck += merch1.getPrice();
        }
        Double amount = purchaseCreationDTO.getAmount();
        PaymentOption paymentOption = PaymentOption.valueOf(purchaseCreationDTO.getPaymentOption());
        String cardNumber = purchaseCreationDTO.getCardNumber();
        Integer cardCvv = purchaseCreationDTO.getCardCvv();
        if (appUser == null){
            return new ResponseEntity<>("Invalid user", HttpStatus.UNAUTHORIZED);
        }
        if (comics.size() == 0 && merch.size() == 0){
            return new ResponseEntity<>("Nothing to purchase", HttpStatus.FORBIDDEN);
        }
        if (!amountCheck.equals(amount)){
            return new ResponseEntity<>("Total purchase amount does not match the sum of products prices", HttpStatus.FORBIDDEN);
        }
        if (amount == 0){
            return new ResponseEntity<>("Purchase amount must be higher than zero", HttpStatus.FORBIDDEN);
        }
        if (purchaseCreationDTO.getPaymentOption().isEmpty()){
            return new ResponseEntity<>("Not a valid payment option", HttpStatus.FORBIDDEN);
        }
        if (paymentOption == PaymentOption.CARD && cardNumber.isEmpty()){
            return new ResponseEntity<>("Missing card number", HttpStatus.FORBIDDEN);
        }
        if (paymentOption == PaymentOption.CASH){
            Purchase newPurchase = new Purchase(appUser, comics, merch, amount, paymentOption);
            purchaseService.save(newPurchase);
        } else {
            Purchase newPurchase = new Purchase(appUser, comics, merch, amount, paymentOption, cardNumber, cardCvv);
            purchaseService.save(newPurchase);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/purchases/export/pdf")
    public ResponseEntity<Object> exportTransactionsPdf(HttpServletResponse response, Authentication authentication, @RequestParam Long purchaseId) throws IOException {
        Purchase purchase = purchaseService.getById(purchaseId);
        AppUser appUser = appUserService.getByEmail(authentication.getName());
        exporter.exportToPDF(purchase, appUser, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
