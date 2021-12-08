package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Merch;
import com.mindhub.ecommerce.repositories.MerchRepository;
import com.mindhub.ecommerce.services.MerchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MerchServiceImpl implements MerchService {

    @Autowired
    MerchRepository merchRepository;

    @Override
    public List<Merch> getAll() {
        return merchRepository.findAll();
    }

    @Override
    public Merch getById(Long id) {
        return merchRepository.findById(id).get();
    }

    @Override
    public Merch save(Merch merch) {
        return merchRepository.save(merch);
    }
}
