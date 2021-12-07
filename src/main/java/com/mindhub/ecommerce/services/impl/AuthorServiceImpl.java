package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Author;
import com.mindhub.ecommerce.repositories.AuthorRepository;
import com.mindhub.ecommerce.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    ;


}
