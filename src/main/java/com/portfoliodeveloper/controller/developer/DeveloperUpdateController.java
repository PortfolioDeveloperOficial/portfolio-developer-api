package com.portfoliodeveloper.controller.developer;

import com.portfoliodeveloper.controller.Resource;
import com.portfoliodeveloper.entity.Developer;
import com.portfoliodeveloper.service.developer.UpdateDeveloper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Developer", description = "Developer API")
public class DeveloperUpdateController implements Resource {
  private final UpdateDeveloper updateDeveloper;

  @PutMapping(value = DEVELOPER_ID)
  @Operation(summary = "Update developer")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "SignUp successfully created"),
        @ApiResponse(responseCode = "400", description = "A validation error was thrown"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
      })
  public void execute(@PathVariable UUID id, @RequestBody Developer.DTO dto) {
    dto.setId(id);
    this.updateDeveloper.execute(dto);
  }
}
