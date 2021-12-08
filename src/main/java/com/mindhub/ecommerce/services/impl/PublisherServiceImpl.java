package com.mindhub.ecommerce.services.impl;

import com.mindhub.ecommerce.models.Publisher;
import com.mindhub.ecommerce.repositories.PublisherRepository;
import com.mindhub.ecommerce.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getById(Long id) {
        return publisherRepository.findById(id).get();
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}
