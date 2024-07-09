package com.portfoliodeveloper.service.developer;

import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.exception.BadRequestException;
import com.portfoliodeveloper.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UpdateDeveloperTest {
    @Mock private DeveloperRepository developerRepository;
    @InjectMocks private UpdateDeveloper updateDeveloper;

    @Test
    void shouldNotUpdateDeveloperWhenDeveloperDoesNotExists() {
        var dto = Developer.DTO.builder().id(UUID.randomUUID()).build();

        given(developerRepository.findById(dto.getId())).willReturn(Optional.empty());

        assertThatThrownBy(() -> updateDeveloper.execute(dto))
            .isInstanceOf(BadRequestException.class)
            .hasMessage(BadRequestException.DEVELOPER_NOT_EXISTS);
    }

    @Test
    void shouldUpdateDeveloper() {
        var dto = Developer.DTO.builder().id(UUID.randomUUID()).build();
        var developer = Developer.create(dto);

        given(developerRepository.findById(dto.getId())).willReturn(Optional.of(developer));

        updateDeveloper.execute(dto);

        assertDoesNotThrow(() -> developerRepository.save(any()));
    }
}