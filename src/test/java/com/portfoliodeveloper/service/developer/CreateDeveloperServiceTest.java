package com.portfoliodeveloper.service.developer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import br.com.senioritymeter.security.gateway.SMPasswordEncoder;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class CreateDeveloperServiceTest {
  @Mock private DeveloperRepository developerRepository;
  @Mock private SMPasswordEncoder passwordEncoder;
  @Mock private SendNotificationCodeDeveloperService sendNotificationCodeDeveloperService;

  @InjectMocks private CreateDeveloperService createDeveloperService;

  @Test
  @DisplayName("Should sign up a new developer")
  void testSignUpOk() {
    var developer = buildDeveloperDTO();

    this.mockPasswordEncoder();
    given(developerRepository.existsByEmail(developer.getEmail())).willReturn(false);

    createDeveloperService.execute(developer);

    then(developerRepository).should().save(any(Developer.class));
    then(sendNotificationCodeDeveloperService).should().execute(any(), any());
  }

  @Test
  @DisplayName("Should throw exception when developer already exists")
  void testSignUpDeveloperAlreadyExists() {
    var developer = buildDeveloperDTO();

    this.mockPasswordEncoder();
    given(developerRepository.existsByEmail(developer.getEmail())).willReturn(true);

    assertThatThrownBy(() -> createDeveloperService.execute(developer))
        .isInstanceOf(BadRequestException.class)
        .hasMessage(BadRequestException.DEVELOPER_ALREADY_EXISTS);

    then(developerRepository).should(never()).save(any(Developer.class));
    then(sendNotificationCodeDeveloperService).shouldHaveNoInteractions();
  }

  private Developer.DTO buildDeveloperDTO() {
    return Developer.DTO
        .builder()
        .name("John Doe")
        .email("test@test")
        .pdi("098123")
        .phone("11999999999")
        .build();
  }

  private void mockPasswordEncoder() {
    var password = Mockito.mock(PasswordEncoder.class);
    given(passwordEncoder.get()).willReturn(password);
    given(password.encode(any())).willReturn("encoded");
  }
}
