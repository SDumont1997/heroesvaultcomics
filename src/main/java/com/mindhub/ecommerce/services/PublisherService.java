package com.mindhub.ecommerce.services;

import com.mindhub.ecommerce.models.Publisher;

import java.util.List;

public interface PublisherService {

    public List<Publisher> getAll();
    public Publisher getById(Long id);
    public Publisher save(Publisher publisher);
}
