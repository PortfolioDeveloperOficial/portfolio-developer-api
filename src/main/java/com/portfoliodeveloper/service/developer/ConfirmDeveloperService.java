package com.portfoliodeveloper.service.developer;

import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmDeveloperService {
  private final DeveloperRepository developerRepository;

  public String execute(final Developer.DTO dto) {
    var developer = this.retriveDeveloper(dto);

    this.authenticate(developer);

    return this.generateToken(developer);
  }

  private Developer retriveDeveloper(final Developer.DTO dto) {
    var developer = developerRepository.findByEmail(dto.getEmail());
    if (developer.isEmpty()) {
      throw BadRequestException.developerNotFound();
    }
    return developer.get();
  }

  private void authenticate(Developer developer) {
    // TODO: Implement authentication
  }

  private String generateToken(Developer developer) {
    // TODO: Implement token generation
    return developer.getCode();
  }
}
