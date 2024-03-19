package org.example.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto extends UserCredentialsDto {
  @NotBlank(message = "New password must not be blank")
  @Size(min = 10, message = "New password must be at least 10 characters long")
  private String newPassword;
}
