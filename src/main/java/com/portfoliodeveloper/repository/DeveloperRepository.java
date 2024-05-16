package com.portfoliodeveloper.repository;

import com.portfoliodeveloper.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
}
