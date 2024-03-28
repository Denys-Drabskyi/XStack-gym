package org.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import org.example.dto.TraineeDto;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TraineeControllerTest {
  @Mock
  private TraineeService service;
  @Mock
  private TrainingService trainingService;
  @Mock
  private TrainerService trainerService;
  @InjectMocks
  private TraineeController controller;

  TraineeDto dto = new TraineeDto();
  UserCredentialsDto credentials = new UserCredentialsDto();

  @Test
  @DisplayName("registerTrainee calls service")
  void testCase01() {
    when(service.create(any())).thenReturn(null);
    controller.registerTrainee(dto);

    verify(service, times(1)).create(dto);
  }

  @Test
  @DisplayName("registerTrainee calls service")
  void testCase02() {
    when(service.getWithTrainers(any())).thenReturn(null);
    controller.getTrainee(credentials, "username");

    verify(service, times(1)).getWithTrainers(any());
  }

  @Test
  @DisplayName("updateTrainee calls service")
  void testCase03() {
    when(service.update(any())).thenReturn(null);
    controller.updateTrainee(dto);

    verify(service, times(1)).update(any());
  }

  @Test
  @DisplayName("deleteTrainee calls service")
  void testCase04() {
    doNothing().when(service).deleteByUsername(any());
    controller.deleteTrainee(dto);

    verify(service, times(1)).deleteByUsername(any());
  }

  @Test
  @DisplayName("updateTraineesTrainers calls service")
  void testCase05() {
    when(trainerService.updateTrainers(any())).thenReturn(null);
    controller.updateTraineesTrainers(UpdateTrainersListDto.builder().build());

    verify(trainerService, times(1)).updateTrainers(any());
  }

  @Test
  @DisplayName("getNotAssignedActiveTrainers calls service")
  void testCase06() {
    when(trainerService.getTrainersNotAssignedToTrainee(any())).thenReturn(null);
    controller.getNotAssignedActiveTrainers(UpdateTrainersListDto.builder().build());

    verify(trainerService, times(1)).getTrainersNotAssignedToTrainee(any());
  }

  @Test
  @DisplayName("getTraineeTrainings calls service")
  void testCase07() {
    when(trainingService.getTraineeTrainingListByTrainerAndDateBetween(any(), any(), any(), any())).thenReturn(null);
    controller.getTraineeTrainings(credentials, List.of(), new Date(), new Date());

    verify(trainingService, times(1)).getTraineeTrainingListByTrainerAndDateBetween(any(), any(), any(), any());
  }


}