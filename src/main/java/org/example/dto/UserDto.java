package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserDto extends UserCredentialsDto {
  @NotBlank(message = "Firstname must not be blank")
  private String firstName;
  @NotBlank(message = "Lastname must not be blank")
  private String lastName;
  private boolean isActive;
}
