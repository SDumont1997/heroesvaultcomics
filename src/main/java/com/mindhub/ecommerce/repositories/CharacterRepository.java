package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
}
