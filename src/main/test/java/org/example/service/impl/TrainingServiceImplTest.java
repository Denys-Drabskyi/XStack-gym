package org.example.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.example.dao.TrainingDao;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.TrainingDto;
import org.example.entity.Training;
import org.example.entity.TrainingType;
import org.example.mapper.TrainingMapper;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingTypeService;
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
  @Mock
  private TrainingMapper trainingMapper;
  @Mock
  private TrainingTypeService trainingTypeService;

  @InjectMocks
  private TrainingServiceImpl service;
  private static final TraineeDto TRAINEE_DTO = TraineeDto.builder().username("test").id(UUID.randomUUID()).build();
  private static final TrainerDto TRAINER_DTO = TrainerDto.builder().username("test").id(UUID.randomUUID()).build();
  private static final Training TRAINING = Training.builder().build();
  private static final TrainingDto DTO = TrainingDto.builder()
      .traineeUsername("traineeUsername")
      .trainerUsername("trainerUsername")
      .trainingType("test")
      .build();
  private static final TrainingType TRAINING_TYPE = new TrainingType(UUID.randomUUID(), "test");

  @Test
  @DisplayName("getTraineeTrainingListByTrainerAndDateBetween() test")
  void testCase01() {
    when(trainingDao.getTraineeTrainingListByTrainerAndDateBetween(anyString(), any(), any(),any())).thenReturn(new ArrayList<>());
    service.getTraineeTrainingListByTrainerAndDateBetween("traineeUsername", List.of("test"), new Date(), new Date());
    verify(trainingDao, times(1)).getTraineeTrainingListByTrainerAndDateBetween(anyString(), any(), any(), any());
  }

  @Test
  @DisplayName("getTraineeTrainingListByTrainerAndDateBetween() test")
  void testCase02() {
    when(trainingDao.getTrainerTrainingListByTraineeAndDateBetween(anyString(), any(), any(),any())).thenReturn(new ArrayList<>());
    service.getTrainerTrainingListByTraineeAndDateBetween("trainerUsername", List.of("test"), new Date(), new Date());
    verify(trainingDao, times(1)).getTrainerTrainingListByTraineeAndDateBetween(anyString(), any(), any(), any());
  }
}