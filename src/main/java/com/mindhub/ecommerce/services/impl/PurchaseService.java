package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Purchase;
import com.mindhub.ecommerce.repositories.PurchaseRepository;
import com.mindhub.ecommerce.services.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> getAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
