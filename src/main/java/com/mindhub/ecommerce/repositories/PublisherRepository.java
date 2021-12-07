package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
