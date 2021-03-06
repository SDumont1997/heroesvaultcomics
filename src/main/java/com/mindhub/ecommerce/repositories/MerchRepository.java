package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Merch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MerchRepository extends JpaRepository<Merch, Long> {
}
