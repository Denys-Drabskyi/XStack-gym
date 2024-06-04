package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto extends UserCredentialsDto {
  @NotBlank(message = "New password must not be blank", groups = OnAuth.class)
  @Size(min = 10, message = "New password must be at least 10 characters long")
  private String newPassword;
}
