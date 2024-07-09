package com.portfoliodeveloper.service.developer;

import br.com.senioritymeter.security.gateway.SMPasswordEncoder;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.repository.DeveloperRepository;
import com.portfoliodeveloper.utility.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginDeveloper {
  private final DeveloperRepository developerRepository;
  private final RetrieveDeveloper retrieveDeveloper;
  private final SMPasswordEncoder passwordEncoder;
  private final SendNotificationCodeDeveloperService sendNotification;

  public void execute(final Developer.DTO dto) {
    var developer = this.retrieveDeveloper.execute(dto);

    String code = CodeGenerator.generate();

    developer.setCode(this.passwordEncoder.get().encode(code));

    this.developerRepository.save(developer);
    this.sendNotification.execute(dto.getEmail(), code);
  }
}
