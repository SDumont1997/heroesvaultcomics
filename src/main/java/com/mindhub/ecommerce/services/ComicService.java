package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Comic;

import java.util.List;

public interface ComicService {

    public List<Comic> getAll();
    public Comic getById(Long id);
    public Comic save(Comic comic);
}
