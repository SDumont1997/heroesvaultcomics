package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Purchase;

import java.util.List;

public interface IPurchaseService {
    public List<Purchase> getAll();
    public Purchase getById(Long id);
    public Purchase save(Purchase purchase);
}
