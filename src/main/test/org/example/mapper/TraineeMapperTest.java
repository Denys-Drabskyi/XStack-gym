package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.UUID;
import org.example.entity.Trainee;
import org.example.util.PasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TraineeMapperTest {
  private final TraineeMapperImpl mapper = new TraineeMapperImpl();
  private static final Trainee TRAINEE = Trainee.builder()
      .id(UUID.randomUUID())
      .firstName("firstname")
      .lastName("lastname")
      .username("firstname.lastname")
      .password(PasswordGenerator.generatePassword())
      .isActive(true)
      .birthDate(new Date())
      .address("address")
      .build();

  private static final Trainee TRAINEE_REZ = Trainee.builder()
      .id(UUID.randomUUID())
      .firstName("firstName1")
      .lastName("lastName1")
      .username("firstName1.lastName1")
      .password(PasswordGenerator.generatePassword())
      .isActive(false)
      .birthDate(new Date())
      .address("newAddress")
      .build();
  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase01() {

    mapper.updateEntityFromEntity(TRAINEE, TRAINEE_REZ);

    assertEquals(TRAINEE.getFirstName(), TRAINEE_REZ.getFirstName());
    assertEquals(TRAINEE.getLastName(), TRAINEE_REZ.getLastName());
    assertEquals(TRAINEE.isActive(), TRAINEE_REZ.isActive());
    assertEquals(TRAINEE.getBirthDate(), TRAINEE_REZ.getBirthDate());
    assertEquals(TRAINEE.getAddress(), TRAINEE_REZ.getAddress());
    assertEquals(TRAINEE.isActive(), TRAINEE_REZ.isActive());

    assertNotEquals(TRAINEE.getUsername(), TRAINEE_REZ.getUsername());
    assertNotEquals(TRAINEE.getId(), TRAINEE_REZ.getId());
  }

  @Test
  @DisplayName("toBuilder() test")
  void testCase04() {
    Trainee rez = mapper.toBuilder(TRAINEE).build();

    assertEquals(TRAINEE.getFirstName(), rez.getFirstName());
    assertEquals(TRAINEE.getLastName(), rez.getLastName());
    assertEquals(TRAINEE.isActive(), rez.isActive());
    assertEquals(TRAINEE.getBirthDate(), rez.getBirthDate());
    assertEquals(TRAINEE.getAddress(), rez.getAddress());
    assertEquals(TRAINEE.getUsername(), rez.getUsername());
    assertEquals(TRAINEE.getId(), rez.getId());
    assertEquals(TRAINEE.isActive(), rez.isActive());
  }
}