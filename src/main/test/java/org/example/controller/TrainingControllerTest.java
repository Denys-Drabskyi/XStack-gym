package org.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.service.TrainingService;
import org.example.service.TrainingTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainingControllerTest {
  @Mock
  private TrainingService service;
  @Mock
  private TrainingTypeService trainingTypeService;
  @InjectMocks
  private TrainingController controller;

  TrainingDto trainingDto = new TrainingDto();
  UserCredentialsDto credentialsDto = new UserCredentialsDto();

  @Test
  @DisplayName("addTraining calls service")
  void testCase01() {
    when(service.create(any())).thenReturn(null);
    controller.addTraining(trainingDto);

    verify(service, times(1)).create(any());
  }

  @Test
  @DisplayName("getTypes calls service")
  void testCase02() {
    when(trainingTypeService.getTypes()).thenReturn(null);
    controller.getTypes(credentialsDto);

    verify(trainingTypeService, times(1)).getTypes();
  }
}