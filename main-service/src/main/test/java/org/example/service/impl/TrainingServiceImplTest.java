package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import org.example.dto.TrainingDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TrainingServiceImplTest {

  @Autowired
  private TrainingServiceImpl service;
  private final TrainingDto trainingDto = TrainingDto.builder()
      .duration(3600)
      .name("name")
      .trainerUsername("trainer.trainer")
      .traineeUsername("trainee.trainee")
      .trainingType("Cardio")
      .date(LocalDate.of(2040, 10, 5))
      .build();

  @Test
  @DisplayName("create() test")
  void testCase01() {
    TrainingDto rez = service.create(trainingDto);
    assertEquals(trainingDto.getTrainingType(), rez.getTrainingType());
    assertEquals(trainingDto.getDuration(), rez.getDuration());
    assertEquals(trainingDto.getDate(), rez.getDate());
    assertEquals(trainingDto.getName(), rez.getName());
    assertEquals(trainingDto.getTrainerUsername(), rez.getTrainerUsername());
    assertEquals(trainingDto.getTraineeUsername(), rez.getTraineeUsername());
  }

  @ParameterizedTest
  @MethodSource("testCase02Source")
  @DisplayName("getTraineeTrainingListByTrainerAndDateBetween() test")
  void testCase02(List<LocalDate> expected, String traineeUsername, Collection<String> trainerUsernames, Date from,
                  Date to) {
    List<TrainingDto> rez =
        service.getTraineeTrainingListByTrainerAndDateBetween(traineeUsername, trainerUsernames, from, to);
    List<LocalDate> rezDates = rez.stream().map(TrainingDto::getDate).toList();
    assertEquals(expected.size(), rez.size());
    assertTrue(expected.containsAll(rezDates));
  }

  // TODO: 3/30/2024 знайти як Date створювати
  public static Stream<Arguments> testCase02Source() {
    return Stream.of(
        Arguments.of(List.of(), "trainee.trainee", List.of(), Date.from(Instant.now()), Date.from(Instant.now()))
    );
  }

  @ParameterizedTest
  @MethodSource("testCase03Source")
  @DisplayName("getTrainerTrainingListByTraineeAndDateBetween() test")
  void testCase03(List<LocalDate> expected, String trainerUsername, Collection<String> traineeUsernames, Date from,
                  Date to) {
    List<TrainingDto> rez =
        service.getTrainerTrainingListByTraineeAndDateBetween(trainerUsername, traineeUsernames, from, to);
    List<LocalDate> rezDates = rez.stream().map(TrainingDto::getDate).toList();
    assertEquals(expected.size(), rez.size());
    assertTrue(expected.containsAll(rezDates));
  }

  // TODO: 3/30/2024 знайти як Date створювати
  public static Stream<Arguments> testCase03Source() {
    return Stream.of(
        Arguments.of(List.of(), "trainee.trainee", List.of(), Date.from(Instant.now()), Date.from(Instant.now()))
    );
  }

  @Test
  void testCase04() {
    assertEquals(2 / 3.0, service.getAverageTrainerTrainingsCount());
  }
}