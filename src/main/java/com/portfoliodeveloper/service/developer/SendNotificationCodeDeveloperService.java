package com.portfoliodeveloper.service.developer;

import br.com.senioritymeter.notification.interaction.NotificationCreation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendNotificationCodeDeveloperService {
  private final NotificationCreation notificationCreation;

  public void execute(final String email, final String code) {
    System.out.println("CODE: " + code);
    // TODO - Remove comments of email sender
    //    final var input =
    //        NotificationCreation.Input.builder()
    //            .email(
    //                NotificationCreation.Input.Email.builder()
    //                    .content("Código de confirmação: " + code)
    //                    .fromEmail("portfoliodeveloper@gmail.com")
    //                    .subject("Portfolio Developer - Email Confirmation")
    //                    .toEmails(List.of(email))
    //                    .build())
    //            .type(NotificationType.EMAIL)
    //            .build();
    //
    //    notificationCreation.execute(input);
  }
}
