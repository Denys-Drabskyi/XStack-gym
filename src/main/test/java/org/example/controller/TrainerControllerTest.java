package org.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import org.example.dto.TrainerDto;
import org.example.dto.UpdateTrainersListDto;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainerControllerTest {
  @Mock
  TrainerService service;
  @Mock
  TrainingService trainingService;
  @InjectMocks
  TrainerController controller;

  TrainerDto dto = new TrainerDto();

  @Test
  @DisplayName("registerTrainer calls service")
  void testCase01() {
    when(service.create(any())).thenReturn(null);
    controller.registerTrainer(dto);

    verify(service, times(1)).create(dto);
  }

  @Test
  @DisplayName("registerTrainee calls service")
  void testCase02() {
    when(service.getByUsername(any())).thenReturn(null);
    controller.getTrainer(dto, "username");

    verify(service, times(1)).getByUsername(any());
  }

  @Test
  @DisplayName("updateTrainee calls service")
  void testCase03() {
    when(service.update(any())).thenReturn(null);
    controller.updateTrainer(dto);

    verify(service, times(1)).update(any());
  }

  @Test
  @DisplayName("getUnassignedTrainers calls service")
  void testCase05() {
    when(service.getTrainersNotAssignedToTrainee(any())).thenReturn(null);
    controller.getUnassignedTrainers(UpdateTrainersListDto.builder().build());

    verify(service, times(1)).getTrainersNotAssignedToTrainee(any());
  }

  @Test
  @DisplayName("getTrainerTrainings calls service")
  void testCase06() {
    when(trainingService.getTrainerTrainingListByTraineeAndDateBetween(any(), any(), any(), any())).thenReturn(null);
    controller.getTrainerTrainings(dto, List.of(), new Date(), new Date());

    verify(trainingService, times(1)).getTrainerTrainingListByTraineeAndDateBetween(any(), any(), any(), any());
  }
}