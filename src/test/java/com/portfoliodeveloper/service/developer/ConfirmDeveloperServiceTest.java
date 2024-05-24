package com.portfoliodeveloper.service.developer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;

import br.com.senioritymeter.security.interaction.AuthenticateUser;
import br.com.senioritymeter.security.interaction.GenerateToken;
import com.portfoliodeveloper.PortfolioDeveloperIntegration;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConfirmDeveloperServiceTest {

  @Mock private DeveloperRepository developerRepository;
  @Mock private AuthenticateUser authenticateUser;
  @Mock private GenerateToken generateToken;
  @InjectMocks private ConfirmDeveloperService confirmDeveloperService;

  @Test
  @DisplayName("Should confirm a developer")
  void shouldConfirmDeveloperOk() {
    var dto = PortfolioDeveloperIntegration.buildDeveloperDTO();
    var developer = Developer.create(dto);

    given(developerRepository.findByEmail(PortfolioDeveloperIntegration.EMAIL))
        .willReturn(Optional.of(developer));

    confirmDeveloperService.execute(dto);

    then(developerRepository).should().findByEmail(PortfolioDeveloperIntegration.EMAIL);
    then(authenticateUser).should().authenticate(eq(dto.getEmail()), eq(dto.getCode()));
    then(generateToken).should().execute(any(GenerateToken.Input.class));
  }

  @Test
  @DisplayName("Should not confirm a developer when developer does not exists")
  void shouldNotConfirmDeveloperOkWhenDeveloperDoesNotExists() {
    var dto = PortfolioDeveloperIntegration.buildDeveloperDTO();

    given(developerRepository.findByEmail(PortfolioDeveloperIntegration.EMAIL))
        .willReturn(Optional.empty());

    assertThatThrownBy(() -> confirmDeveloperService.execute(dto))
        .isInstanceOf(BadRequestException.class)
        .hasMessage(BadRequestException.DEVELOPER_NOT_EXISTS);

    then(developerRepository).should().findByEmail(PortfolioDeveloperIntegration.EMAIL);
  }

  @Test
  @DisplayName("Should not confirm a developer when authenticate is failed")
  void shouldNotConfirmDeveloperOkWhenAuthenticateIsFailed() {
    var dto = PortfolioDeveloperIntegration.buildDeveloperDTO();
    var developer = Developer.create(dto);

    given(developerRepository.findByEmail(PortfolioDeveloperIntegration.EMAIL))
        .willReturn(Optional.of(developer));
    doThrow(new RuntimeException("any erro")).when(authenticateUser).authenticate(any(), any());

    assertThatThrownBy(() -> confirmDeveloperService.execute(dto))
        .isInstanceOf(BadRequestException.class)
        .hasMessage(BadRequestException.BAD_CREDENTIALS);

    then(developerRepository).should().findByEmail(PortfolioDeveloperIntegration.EMAIL);
    then(generateToken).should(never()).execute(any());
  }
}
