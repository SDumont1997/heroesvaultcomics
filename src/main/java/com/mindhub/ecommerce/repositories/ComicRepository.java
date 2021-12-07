package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComicRepository extends JpaRepository<Comic, Long> {
}
