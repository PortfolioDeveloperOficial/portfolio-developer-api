package com.portfoliodeveloper.service.developer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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
  }

  @Test
  @DisplayName("Should not confirm a developer")
  void shouldNotConfirmDeveloperOk() {
    var dto = PortfolioDeveloperIntegration.buildDeveloperDTO();

    given(developerRepository.findByEmail(PortfolioDeveloperIntegration.EMAIL))
        .willReturn(Optional.empty());

    assertThatThrownBy(() -> confirmDeveloperService.execute(dto))
        .isInstanceOf(BadRequestException.class)
        .hasMessage(BadRequestException.DEVELOPER_NOT_EXISTS);

    then(developerRepository).should().findByEmail(PortfolioDeveloperIntegration.EMAIL);
  }
}
