package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrainerMapperTest {
  private final TrainerMapperImpl mapper = new TrainerMapperImpl();
  private static final Trainer TRAINER = new Trainer("firstname", "lastname", TrainingType.TYPE_1);

  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase01() {
    Trainer rez = new Trainer("firstname1", "lastname1", TrainingType.TYPE_2);
    mapper.updateEntityFromEntity(TRAINER, rez);

    assertEquals(TRAINER.getFirstName(), rez.getFirstName());
    assertEquals(TRAINER.getLastName(), rez.getLastName());
    assertEquals(TRAINER.isActive(), rez.isActive());
    assertEquals(TRAINER.getSpecialization(), rez.getSpecialization());

    assertNotEquals(TRAINER.getUsername(), rez.getUsername());
  }
}