package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.ComicCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicCharacterRepository extends JpaRepository<ComicCharacter, Long> {
}
