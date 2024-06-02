package com.portfoliodeveloper.service.developer;

import br.com.senioritymeter.security.gateway.SMPasswordEncoder;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import com.portfoliodeveloper.utility.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDeveloperService {
  private final DeveloperRepository developerRepository;
  private final SendNotificationCodeDeveloperService sendNotification;
  private final SMPasswordEncoder passwordEncoder;

  public void execute(final Developer.DTO dto) {
    String code = CodeGenerator.generate();
    dto.setCode(this.passwordEncoder.get().encode(code));
    final Developer developer = Developer.create(dto);
    if (this.developerRepository.existsByEmail(developer.getEmail())) {
      throw BadRequestException.developerAlreadyExists();
    }
    this.developerRepository.save(developer);
    this.sendNotification.execute(dto.getEmail(), code);
  }
}
