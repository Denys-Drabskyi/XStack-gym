package org.example.entity;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Trainee extends User {
  private Date birthDate;
  private String address;

  @Builder
  public Trainee(UUID id, String firstName, String lastName, String username, String password, boolean isActive,
                 Date birthDate, String address) {
    super(id, firstName, lastName, username, password, isActive);
    this.birthDate = birthDate;
    this.address = address;
  }

  public static class TraineeBuilder {
    public TraineeBuilder() {}
  }
}
