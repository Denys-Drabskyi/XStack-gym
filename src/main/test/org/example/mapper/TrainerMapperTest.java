package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.util.PasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrainerMapperTest {
  private final TrainerMapperImpl mapper = new TrainerMapperImpl();
  private static final Trainer TRAINER = Trainer.builder()
      .id(UUID.randomUUID())
      .firstName("firstname")
      .lastName("lastname")
      .username("firstname.lastname")
      .password(PasswordGenerator.generatePassword())
      .isActive(true)
      .specialization(TrainingType.TYPE_1)
      .build();

  private static final Trainer TRAINER_REZ = Trainer.builder()
      .id(UUID.randomUUID())
      .firstName("firstname1")
      .lastName("lastname1")
      .username("random.username")
      .password(PasswordGenerator.generatePassword())
      .isActive(false)
      .specialization(TrainingType.TYPE_2)
      .build();

  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase01() {
    mapper.updateEntityFromEntity(TRAINER, TRAINER_REZ);

    assertEquals(TRAINER.getFirstName(), TRAINER_REZ.getFirstName());
    assertEquals(TRAINER.getLastName(), TRAINER_REZ.getLastName());
    assertEquals(TRAINER.isActive(), TRAINER_REZ.isActive());
    assertEquals(TRAINER.getSpecialization(), TRAINER_REZ.getSpecialization());
    assertEquals(TRAINER.isActive(), TRAINER_REZ.isActive());

    assertNotEquals(TRAINER.getUsername(), TRAINER_REZ.getUsername());
    assertNotEquals(TRAINER.getId(), TRAINER_REZ.getId());
  }

  @Test
  @DisplayName("toBuilder() test")
  void testCase02() {
    Trainer rez = mapper.toBuilder(TRAINER).build();

    assertEquals(TRAINER.getFirstName(), rez.getFirstName());
    assertEquals(TRAINER.getLastName(), rez.getLastName());
    assertEquals(TRAINER.isActive(), rez.isActive());
    assertEquals(TRAINER.getSpecialization(), rez.getSpecialization());
    assertEquals(TRAINER.getUsername(), rez.getUsername());
    assertEquals(TRAINER.getId(), rez.getId());
    assertEquals(TRAINER.isActive(), rez.isActive());
  }
}