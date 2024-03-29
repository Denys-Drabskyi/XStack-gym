package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDto implements ValidatedDto {
  @JsonView({Views.InList.class, Views.Credentials.class})
  @NotBlank(message = "Username must not be blank", groups = OnAuth.class)
  private String username;

  @JsonView(Views.Credentials.class)
  @NotBlank(message = "Password must not be blank", groups = OnAuth.class)
  private String password;

  @JsonView(Views.Credentials.class)
  private String jwt;
}
