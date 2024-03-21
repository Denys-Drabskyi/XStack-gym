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
public class UserDto extends UserCredentialsDto {
  @JsonView(Views.InList.class)
  @NotBlank(message = "Firstname must not be blank")
  private String firstName;

  @JsonView(Views.InList.class)
  @NotBlank(message = "Lastname must not be blank")
  private String lastName;

  @JsonView({Views.PublicTrainee.class, Views.PublicTrainer.class})
  private boolean active;
}
