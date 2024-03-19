package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;
import org.example.TestConfig;
import org.example.dto.TrainingDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.Training;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class TrainingMapperTest {

  @Autowired
  private TrainingMapper trainingMapper;
  private final LocalDate date = LocalDate.of(2000, 1 ,1);

  private final Training training = Training.builder()
      .id(UUID.randomUUID())
      .type(new TrainingType(UUID.randomUUID(), "type1"))
      .trainer(Trainer.builder().user(User.builder().username("trainerUsername").build()).build())
      .trainee(Trainee.builder().user(User.builder().username("traineeUsername").build()).build())
      .name("name")
      .date(Date.from(date.atStartOfDay().toInstant(ZoneOffset.UTC)))
      .duration(3600)
      .build();

  private final TrainingDto dto = TrainingDto.builder()
      .trainingType("type1")
      .trainerUsername("trainerUsername")
      .traineeUsername("traineeUsername")
      .name("name")
      .date(date)
      .duration(3600)
      .build();

  @Test
  @DisplayName("toDto() test")
  void testCase01() {
    TrainingDto rez = trainingMapper.toDto(training);

    assertEquals(dto.getName(), rez.getName());
    assertEquals(dto.getTrainerUsername(), rez.getTrainerUsername());
    assertEquals(dto.getTraineeUsername(), rez.getTraineeUsername());
    assertEquals(dto.getDate(), rez.getDate());
    assertEquals(dto.getDuration(), rez.getDuration());
    assertEquals(dto.getTrainingType(), rez.getTrainingType());
  }

  @Test
  @DisplayName("toEntity() test")
  void testCase02() {
    Training rez = trainingMapper.toEntity(dto);

    assertEquals(training.getName(), rez.getName());
    assertEquals(training.getTrainee().getUser().getUsername(), rez.getTrainee().getUser().getUsername());
    assertEquals(training.getTrainer().getUser().getUsername(), rez.getTrainer().getUser().getUsername());
    assertEquals(training.getDate(), rez.getDate());
    assertEquals(training.getDuration(), rez.getDuration());
    assertEquals(training.getType().getName(), rez.getType().getName());
  }
}