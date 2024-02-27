package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.example.dao.TrainingDao;
import org.example.entity.Training;
import org.example.entity.TrainingType;
import org.example.exception.EntityNotFoundException;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

  @Mock
  private TrainingDao trainingDao;
  @Mock
  private TrainerService trainerService;
  @Mock
  private TraineeService traineeService;

  @InjectMocks
  private TrainingServiceImpl service;

  private static final Training TRAINING = new Training(
      UUID.randomUUID(),
      UUID.randomUUID(),
      UUID.randomUUID(),
      "",
      TrainingType.TYPE_1,
      new Date(),
      0L
  );

  @Test
  @DisplayName("getById() throws EntityNotFoundException when there is no such training")
  void testCase01() {
    when(trainingDao.get(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getById() returns training")
  void testCase02() {
    when(trainingDao.get(any())).thenReturn(Optional.of(TRAINING));

    assertEquals(TRAINING, service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("create() creates training")
  void testCase03() {
    when(traineeService.getById(any())).thenReturn(null);
    when(trainerService.getById(any())).thenReturn(null);
    when(trainingDao.save(TRAINING)).thenReturn(TRAINING);

    assertEquals(TRAINING, service.create(TRAINING));
  }
}