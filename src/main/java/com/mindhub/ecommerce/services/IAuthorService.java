package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Author;

import java.util.List;

public interface IAuthorService {

    public List<Author> getAll();
    public Author getById(Long id);
    public Author save(Author author);
}
