package com.example.trainersservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.trainersservice.dao.InMemoryTrainingSummaryDao;
import com.example.trainersservice.dao.TrainingSummaryDao;
import com.example.trainersservice.entity.TrainingMonth;
import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.entity.TrainingYear;
import com.example.trainersservice.exception.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.example.dto.ActionType;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InMemoryTrainingSummaryServiceTest {
  List<TrainingSummary> storage;
  DefaultTrainingSummaryService service;
  TrainingSummaryDao dao;

  RegisterTrainingEventDto.RegisterTrainingEventDtoBuilder builder = RegisterTrainingEventDto.builder()
      .firstname("John")
      .lastname("Doe")
      .isActive(true)
      .trainingDuration(100);

  @BeforeEach
  void setUp() {
    storage = new ArrayList<>();
    storage.add(trainer1);
    storage.add(trainer2);
    dao = new InMemoryTrainingSummaryDao(storage);
    service = new DefaultTrainingSummaryService(dao);
  }

  @Test
  @DisplayName("processTrainingEvent() creates new month on ActionType.ADD when there is no data about month of training in dto")
  void testCase01() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.ADD)
        .build();
    int sizeBefore = trainer2.getYears().getFirst().getMonths().size();

    service.processTrainingEvent(dto);

    assertEquals(sizeBefore + 1, storage.size());
  }

  @Test
  @DisplayName("processTrainingEvent() adds totalTrainingDuration on ActionType.ADD if there is data about month of training in dto")
  void testCase02() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 1, 1)))
        .actionType(ActionType.ADD)
        .build();
    int sizeBefore = storage.size();

    service.processTrainingEvent(dto);

    assertEquals(sizeBefore, storage.size());
    assertEquals(220L, trainer2.getYears().getFirst().getMonths().getFirst().getSummaryDuration());
  }

  @Test
  @DisplayName("processTrainingEvent() throws EntityNotFoundException on ActionType.DELETE when there is no data about month of training in dto")
  void testCase03() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.DELETE)
        .build();

    assertThrows(EntityNotFoundException.class, () -> service.processTrainingEvent(dto));
  }

  @Test
  @DisplayName("processTrainingEvent() subtracts totalTrainingDuration on ActionType.ADD if there is data about month of training in dto")
  void testCase04() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 1, 1)))
        .actionType(ActionType.DELETE)
        .build();

    service.processTrainingEvent(dto);

    assertEquals(20L, trainer2.getYears().getFirst().getMonths().getFirst().getSummaryDuration());
  }

  @Test
  @DisplayName("getTrainerStatistic() returns null when there is no data about trainer months")
  void testCase05() {
    assertNull(service.getTrainerStatistic("john_trainer10"));
  }

  @Test
  @DisplayName("getTrainerStatistic() returns null when there is no data about trainer months")
  void testCase06() {
    var expectedStats = Map.of(
        2023, Map.of(1, 120L),
        2024, Map.of(
            3, 120L,
            5, 120L,
            7, 120L
        )
    );
    var expected = TrainerStatisticsDto.builder()
        .username("john_trainer")
        .firstname("John")
        .lastname("Doe")
        .duration(expectedStats)
        .isActive(false)
        .build();

    assertEquals(expected, service.getTrainerStatistic("john_trainer"));
  }

  @Test
  @DisplayName("processTrainingEvent() throws EntityNotFoundException on ActionType.DELETE when there is no data about month of training in dto")
  void testCase07() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2025, 2, 1)))
        .actionType(ActionType.DELETE)
        .build();

    assertThrows(EntityNotFoundException.class, () -> service.processTrainingEvent(dto));
  }

  @Test
  @DisplayName("processTrainingEvent() throws EntityNotFoundException on ActionType.DELETE when there is no data about month of training in dto")
  void testCase08() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.ADD)
        .build();
    var trainerYear = trainer2.getYears().getFirst();
    var before = trainerYear.getMonths().size();
    service.processTrainingEvent(dto);
    assertEquals(before + 1, trainerYear.getMonths().size());
  }

  @Test
  @DisplayName("processTrainingEvent() throws EntityNotFoundException on ActionType.DELETE when there is no data about trainer")
  void testCase09() {
    var dto = builder
        .username("john_trainer12")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.DELETE)
        .build();
    assertThrows(EntityNotFoundException.class, () -> service.processTrainingEvent(dto));
  }

  @Test
  @DisplayName("processTrainingEvent() creates new summary if not exists")
  void testCase10() {
    var dto = builder
        .username("john_trainer12")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.ADD)
        .build();

    var before = storage.size();
    service.processTrainingEvent(dto);
    assertEquals(before + 1, storage.size());
  }

  TrainingSummary trainer1 = TrainingSummary.builder()
      .username("john_trainer")
      .firstname("John")
      .lastname("Doe")
      .years(List.of(
              Y2024(List.of(
                  MONTH(5, 120L),
                  MONTH(3, 120L),
                  MONTH(7, 120L))
              ),
              Y2023(List.of(
                  MONTH(1, 120L))
              )
          )
      )
      .isActive(false)
      .build();

  TrainingSummary trainer2 = TrainingSummary.builder()
      .username("john_trainer1")
      .firstname("John")
      .lastname("Doe")
      .years(List.of(Y2024(new ArrayList<>(List.of(MONTH(1, 120L))))))
      .isActive(true)
      .build();

  TrainingYear Y2024(List<TrainingMonth> months) {
    var year = TrainingYear.of(2024);
    year.setMonths(months);
    return year;
  }

  TrainingYear Y2023(List<TrainingMonth> months) {
    var year = TrainingYear.of(2023);
    year.setMonths(months);
    return year;
  }

  private TrainingMonth MONTH(int month, long duration) {
    var trainingMonth = TrainingMonth.of(month);
    trainingMonth.setSummaryDuration(duration);
    return trainingMonth;
  }
}