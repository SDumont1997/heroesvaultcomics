package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Merch;

import java.util.List;

public interface IMerchService {
    public List<Merch> getAll();
    public Merch getById(Long id);
    public Merch save(Merch merch);
}
