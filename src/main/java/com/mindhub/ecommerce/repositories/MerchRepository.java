package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.Merch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchRepository extends JpaRepository<Merch, Long> {
}
