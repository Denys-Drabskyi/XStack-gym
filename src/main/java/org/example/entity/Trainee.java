package org.example.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
public class Trainee implements Entity<UUID> {
  private final UUID id;
  private Date birthDate;
  private String address;
  private final UUID userId;

  public Trainee(Date birthDate, String address, UUID userId) {
    Objects.requireNonNull(birthDate, "Birth date can not be null");
    Objects.requireNonNull(address, "Address date can not be null");
    Objects.requireNonNull(userId, "User id can not be null");
    this.id = UUID.randomUUID();
    this.birthDate = birthDate;
    this.address = address;
    this.userId = userId;
  }

  @Override
  public UUID getId() {
    return this.id;
  }
}
