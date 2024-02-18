package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.example.dto.TrainerDto;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrainerMapperTest {
  private final TrainerMapperImpl mapper = new TrainerMapperImpl();
  private static final TrainerDto TRAINER_DTO = new TrainerDto(UUID.randomUUID(), TrainingType.TYPE_1);
  private static final User USER = new User("firstname", "lastname");
  private static final Trainer TRAINER = new Trainer(TrainingType.TYPE_1, UUID.randomUUID());

  @Test
  @DisplayName("fromUser() test")
  void testCase01() {
    TrainerDto rez = mapper.fromUser(USER);
    assertNull(rez.getUserId());
    assertEquals(USER.getFirstName(), rez.getFirstName());
    assertEquals(USER.getLastName(), rez.getLastName());
    assertEquals(USER.getUsername(), rez.getUsername());
    assertEquals(USER.getPassword(), rez.getPassword());
    assertEquals(USER.isActive(), rez.isActive());
  }

  @Test
  @DisplayName("newTrainerFromDto() test")
  void testCase02() {
    TRAINER_DTO.setUserId(UUID.randomUUID());
    Trainer rez = mapper.newTrainerFromDto(TRAINER_DTO);
    assertEquals(TRAINER_DTO.getUserId(), rez.getUserId());
    assertEquals(TRAINER_DTO.getSpecialization(), rez.getSpecialization());
  }

  @Test
  @DisplayName("updateDtoFromEntity() test")
  void testCase03() {
    TrainerDto rez = new TrainerDto();
    mapper.updateDtoFromEntity(TRAINER, rez);
    assertEquals(TRAINER.getUserId(), rez.getUserId());
    assertEquals(TRAINER.getSpecialization(), rez.getSpecialization());
    assertEquals(TRAINER.getId(), rez.getId());
  }

  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase04() {
    Trainer rez = new Trainer(TrainingType.TYPE_2, UUID.randomUUID());
    mapper.updateEntityFromEntity(TRAINER, rez);
//    because we cannot update userId
    assertNotEquals(TRAINER.getUserId(), rez.getUserId());
    assertEquals(TRAINER.getSpecialization(), rez.getSpecialization());
  }
}