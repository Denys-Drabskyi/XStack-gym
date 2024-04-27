package com.example.trainersservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.trainersservice.entity.TrainerMonth;
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

class InMemoryTrainerServiceTest {
  List<TrainerMonth> storage;
  InMemoryTrainerService service;

  RegisterTrainingEventDto.RegisterTrainingEventDtoBuilder builder = RegisterTrainingEventDto.builder()
      .firstname("John")
      .lastname("Doe")
      .isActive(true)
      .trainingDuration(100);

  @BeforeEach
  void setUp() {
    storage = new ArrayList<>();
    storage.add(month1);
    storage.add(month2);
    storage.add(month3);
    storage.add(month4);
    storage.add(month5);
    service = new InMemoryTrainerService(storage);
  }

  @Test
  @DisplayName("processTrainingEvent() creates new month on ActionType.ADD when there is no data about month of training in dto")
  void testCase01() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.ADD)
        .build();
    int sizeBefore = storage.size();

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
    assertEquals(220L, month5.getTotalTrainingDuration());
  }

  @Test
  @DisplayName("processTrainingEvent() throws IllegalStateException on ActionType.DELETE when there is no data about month of training in dto")
  void testCase03() {
    var dto = builder
        .username("john_trainer1")
        .trainingDate(Date.valueOf(LocalDate.of(2024, 2, 1)))
        .actionType(ActionType.DELETE)
        .build();

    assertThrows(IllegalStateException.class, () -> service.processTrainingEvent(dto));
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

    assertEquals(20L, month5.getTotalTrainingDuration());
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

  TrainerMonth month1 = TrainerMonth.builder()
      .username("john_trainer")
      .firstname("John")
      .lastname("Doe")
      .year(2024)
      .month(7)
      .isActive(false)
      .totalTrainingDuration(120L)
      .build();

  TrainerMonth month2 = TrainerMonth.builder()
      .username("john_trainer")
      .firstname("John")
      .lastname("Doe")
      .year(2024)
      .month(3)
      .isActive(true)
      .totalTrainingDuration(120L)
      .build();

  TrainerMonth month3 = TrainerMonth.builder()
      .username("john_trainer")
      .firstname("John")
      .lastname("Doe")
      .year(2024)
      .month(5)
      .isActive(true)
      .totalTrainingDuration(120L)
      .build();

  TrainerMonth month4 = TrainerMonth.builder()
      .username("john_trainer")
      .firstname("John")
      .lastname("Doe")
      .year(2023)
      .month(1)
      .isActive(true)
      .totalTrainingDuration(120L)
      .build();

  TrainerMonth month5 = TrainerMonth.builder()
      .username("john_trainer1")
      .firstname("John")
      .lastname("Doe")
      .year(2024)
      .month(1)
      .isActive(true)
      .totalTrainingDuration(120L)
      .build();
}