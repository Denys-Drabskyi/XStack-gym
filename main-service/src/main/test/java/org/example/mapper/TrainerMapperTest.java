package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.example.Main;
import org.example.dto.TrainerDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Main.class)
class TrainerMapperTest {

  @Autowired
  private TrainerMapper mapper;

  private final UUID id = UUID.fromString("4aca1d5d-2994-4d35-a0ee-be7743572933");

  private final Trainer trainer = Trainer.builder()
      .id(id)
      .user(User.builder().id(UUID.randomUUID()).firstName("firstname").lastName("lastname").password("password").username("username").build())
      .specialization(new TrainingType(UUID.randomUUID(), "type1"))
      .build();
  private final TrainerDto trainerDto = TrainerDto.builder()
      .id(id)
      .username("username")
      .password("password")
      .firstName("firstname")
      .lastName("lastname")
      .specialization("type1")
      .build();

  private final TrainerDto newTrainerDto = TrainerDto.builder()
      .id(UUID.randomUUID())
      .username("new")
      .password("new")
      .firstName("new")
      .lastName("new")
      .specialization("new")
      .build();

  @Test
  @DisplayName("toDto() test")
  void testCase01() {
    TrainerDto rez = mapper.toDto(trainer);

    assertEquals(trainerDto.getSpecialization(), rez.getSpecialization());
    assertEquals(trainerDto.getFirstName(), rez.getFirstName());
    assertEquals(trainerDto.getLastName(), rez.getLastName());
    assertEquals(trainerDto.getUsername(), rez.getUsername());
    assertEquals(trainerDto.getId(), rez.getId());

    assertNull(rez.getPassword());
  }

  @Test
  @DisplayName("toCredentials() test")
  void testCase02() {
    UserCredentialsDto rez = mapper.toCredentials(trainer);

    assertEquals(trainerDto.getUsername(), rez.getUsername());
    assertEquals(trainerDto.getPassword(), rez.getPassword());
  }

  @Test
  @DisplayName("updateEntityFromDto() test")
  void testCase03() {
    mapper.updateEntityFromDto(newTrainerDto, trainer);

    assertNotEquals(newTrainerDto.getId(), trainer.getId());
    assertNotEquals(newTrainerDto.getUsername(), trainer.getUser().getUsername());
    assertNotEquals(newTrainerDto.getPassword(), trainer.getUser().getPassword());
  }



}