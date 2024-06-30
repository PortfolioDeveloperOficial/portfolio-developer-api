package com.portfoliodeveloper.service.developer;

import static org.assertj.core.api.Assertions.assertThat;
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
class RetrieveDeveloperTest {
  @Mock private DeveloperRepository developerRepository;
  @InjectMocks private RetrieveDeveloper retrieveDeveloper;

  @Test
  @DisplayName("Should retrieve a developer")
  void shouldRetrieveDeveloper() {
    var dto = PortfolioDeveloperIntegration.buildDeveloperDTO();
    var developerExpected = Developer.create(dto);

    given(developerRepository.findByEmail(PortfolioDeveloperIntegration.EMAIL))
        .willReturn(Optional.of(developerExpected));

    var developer = retrieveDeveloper.execute(dto);

    then(developerRepository).should().findByEmail(PortfolioDeveloperIntegration.EMAIL);
    assertThat(developer).usingRecursiveComparison().isEqualTo(developerExpected);
  }

  @Test
  @DisplayName("Should not retrieve a developer when does not exists")
  void shouldNotRetrieveDeveloperWhenDeveloperDoesNotExists() {
    var dto = PortfolioDeveloperIntegration.buildDeveloperDTO();

    given(developerRepository.findByEmail(PortfolioDeveloperIntegration.EMAIL))
        .willReturn(Optional.empty());

    assertThatThrownBy(() -> retrieveDeveloper.execute(dto))
        .isInstanceOf(BadRequestException.class)
        .hasMessage(BadRequestException.DEVELOPER_NOT_EXISTS);

    then(developerRepository).should().findByEmail(PortfolioDeveloperIntegration.EMAIL);
  }
}
