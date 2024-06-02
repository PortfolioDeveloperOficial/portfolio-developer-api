package com.portfoliodeveloper.service.developer;

import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveDeveloperService {
  private final DeveloperRepository developerRepository;

  public Developer execute(final Developer.DTO dto) {
    var developer = developerRepository.findByEmail(dto.getEmail());
    if (developer.isEmpty()) {
      throw BadRequestException.developerNotFound();
    }
    return developer.get();
  }
}
