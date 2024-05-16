package com.portfoliodeveloper.repository;

import com.portfoliodeveloper.entity.Developer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
  boolean existsByEmail(String email);
}
