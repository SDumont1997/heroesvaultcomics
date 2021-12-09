package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Comic;
import com.mindhub.ecommerce.repositories.ComicRepository;
import com.mindhub.ecommerce.services.IComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService implements IComicService {

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
