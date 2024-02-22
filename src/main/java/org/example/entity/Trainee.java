package org.example.entity;

import java.util.Date;
import java.util.Objects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Trainee extends User {
  private Date birthDate;
  private String address;

  @Builder
  public Trainee(String firstName, String lastName, Date birthDate, String address) {
    super(firstName, lastName);
    Objects.requireNonNull(birthDate, "Birth date can not be null");
    Objects.requireNonNull(address, "Address can not be null");
    this.birthDate = birthDate;
    this.address = address;
  }

  public void setBirthDate(Date birthDate) {
    Objects.requireNonNull(birthDate, "Birth date name can not be null");
    this.birthDate = birthDate;
  }

  public void setAddress(String address) {
    Objects.requireNonNull(address, "Address can not be null");
    this.address = address;
  }
}
