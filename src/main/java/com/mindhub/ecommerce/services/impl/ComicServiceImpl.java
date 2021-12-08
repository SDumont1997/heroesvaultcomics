package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Comic;
import com.mindhub.ecommerce.repositories.ComicRepository;
import com.mindhub.ecommerce.services.ComicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComicServiceImpl implements ComicService {

    @Autowired
    ComicRepository comicRepository;

    @Override
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }

    @Override
    public Comic getById(Long id) {
        return comicRepository.findById(id).get();
    }

    @Override
    public Comic save(Comic comic) {
        return comicRepository.save(comic);
    }

}
