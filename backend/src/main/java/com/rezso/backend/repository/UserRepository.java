package com.rezso.backend.repository;

import com.rezso.backend.model.CRM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CRM, Integer> {
//    Optional<User> findUserByUsername(String username);
}
