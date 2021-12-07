package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
