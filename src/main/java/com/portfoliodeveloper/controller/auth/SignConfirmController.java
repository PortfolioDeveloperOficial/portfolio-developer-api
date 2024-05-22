package com.portfoliodeveloper.controller.auth;

import com.portfoliodeveloper.controller.Resource;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.service.developer.ConfirmDeveloperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Sign", description = "Authentication API")
public class SignConfirmController implements Resource {
  private final ConfirmDeveloperService confirmDeveloperService;

  @PostMapping(path = SIGN_CONFIRM)
  @Operation(summary = "Confirm a desenvolvedor.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "SignConfirm successfully"),
        @ApiResponse(responseCode = "400", description = "A validation error was thrown"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  public String execute(@RequestBody Developer.DTO dto) {
    return this.confirmDeveloperService.execute(dto);
  }
}
