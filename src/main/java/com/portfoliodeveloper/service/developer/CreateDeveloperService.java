package com.portfoliodeveloper.service.developer;

import br.com.senioritymeter.notification.enumeration.NotificationType;
import br.com.senioritymeter.notification.interaction.NotificationCreation;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import com.portfoliodeveloper.utility.CodeGenerator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDeveloperService {
  private final DeveloperRepository developerRepository;
  private final NotificationCreation notificationCreation;

  public void execute(final Developer.DTO dto) {
    String code = CodeGenerator.generate();
    dto.setCode(code);
    final Developer developer = Developer.create(dto);
    if (developerRepository.existsByEmail(developer.getEmail())) {
      throw BadRequestException.developerAlreadyExists();
    }
    developerRepository.save(developer);
    this.sendNotification(dto.getEmail(), code);
  }

  private void sendNotification(final String email, final String code) {
    final var input =
        NotificationCreation.Input.builder()
            .email(
                NotificationCreation.Input.Email.builder()
                    .content("Código de confirmação: " + code)
                    .fromEmail("portfoliodeveloper@gmail.com")
                    .subject("Portfolio Developer - Email Confirmation")
                    .toEmails(List.of(email))
                    .build())
            .type(NotificationType.EMAIL)
            .build();

    notificationCreation.execute(input);
  }
}
