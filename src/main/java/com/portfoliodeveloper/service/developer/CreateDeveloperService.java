package com.portfoliodeveloper.service.developer;

import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDeveloperService {
  private final DeveloperRepository developerRepository;

  public void execute(final Developer.DTO dto) {
    final Developer developer = Developer.create(dto);
    developerRepository.save(developer);
  }
}
