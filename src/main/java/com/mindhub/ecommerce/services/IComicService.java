package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Comic;

import java.util.List;

public interface IComicService {

    public List<Comic> getAll();
    public Comic getById(Long id);
    public Comic save(Comic comic);
}
