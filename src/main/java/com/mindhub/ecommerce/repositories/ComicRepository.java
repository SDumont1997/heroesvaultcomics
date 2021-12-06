package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Long> {
}
