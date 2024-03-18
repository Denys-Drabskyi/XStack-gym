package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserCredentialsDto implements ValidatedDto {
  @NotBlank(message = "Username must not be blank")
  private String username;
  @NotBlank(message = "Password must not be blank")
  private String password;
}
