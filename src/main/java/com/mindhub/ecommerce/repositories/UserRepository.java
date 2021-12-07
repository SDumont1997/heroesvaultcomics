package com.mindhub.ecommerce.repositories;

import com.mindhub.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
