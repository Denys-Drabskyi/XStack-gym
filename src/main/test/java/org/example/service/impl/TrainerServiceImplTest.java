package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.example.dao.TraineeDao;
import org.example.dao.TrainerDao;
import org.example.dto.TrainerDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.TrainerMapper;
import org.example.service.TrainingTypeService;
import org.example.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainerServiceImplTest {
  @Mock
  private TrainerDao trainerDao;
  @Mock
  private TraineeDao traineeDao;
  @Mock
  private TrainerMapper trainerMapper;
  @Mock
  private UserService userService;
  @Mock
  private TrainingTypeService trainingTypeService;
  private static final Trainer TRAINER = Trainer.builder().build();
  private static final TrainerDto DTO = TrainerDto.builder().build();
  private static final Trainee TRAINEE = Trainee.builder().trainers(new ArrayList<>()).build();
  private static final String TRAINER_USERNAME = "test.test";

  @InjectMocks
  private TrainerServiceImpl service;

  @Test
  @DisplayName("existsById() returns optional of Trainer")
  void testCase01() {
    when(trainerDao.existById(any())).thenReturn(true);

    assertTrue(service.existsById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("get() throws EntityNotFoundException when there is no such trainee")
  void testCase02() {
    when(trainerDao.getByUsername(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.get(DTO));
  }

  @Test
  @DisplayName("getExistingById() returns trainee")
  void testCase03() {
    when(trainerDao.getByUsername(any())).thenReturn(Optional.of(TRAINER));
    when(trainerMapper.toDto(TRAINER)).thenReturn(DTO);

    assertEquals(DTO, service.get(DTO));
  }

  @Test
  @DisplayName("create() creates trainer")
  void testCase04() {
    when(trainerDao.save(any())).thenReturn(TRAINER);
    when(trainerMapper.toBuilder(DTO)).thenReturn(Trainer.builder());

    service.create(DTO);
    verify(trainerMapper, times(1)).toBuilder(DTO);
    verify(trainerDao, times(1)).save(any());
  }

  @Test
  @DisplayName("update() throws EntityNotFoundException when there is no such trainer")
  void testCase05() {
    when(trainerDao.getByUsername(DTO.getUsername())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.update(DTO));
  }

  @Test
  @DisplayName("update() updates trainer")
  void testCase06() {
    when(trainerDao.getByUsername(DTO.getUsername())).thenReturn(Optional.of(TRAINER));

    service.update(DTO);
    verify(trainerDao, times(1)).save(TRAINER);
  }

  @Test
  @DisplayName("addTrainerToTrainee() throws EntityNotFoundException when there is no trainee matching")
  void testCase07() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, ()-> service.addTrainerToTrainee(DTO, TRAINER_USERNAME));
    verify(traineeDao, times(0)).save(any());
  }

  @Test
  @DisplayName("addTrainerToTrainee() throws EntityNotFoundException when there is no trainer matching")
  void testCase08() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.of(TRAINEE));
    when(trainerDao.getByUsername(TRAINER_USERNAME)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, ()-> service.addTrainerToTrainee(DTO, TRAINER_USERNAME));
    verify(traineeDao, times(0)).save(any());
  }

  @Test
  @DisplayName("addTrainerToTrainee() adds trainer to trainee")
  void testCase09() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.of(TRAINEE));
    when(trainerDao.getByUsername(TRAINER_USERNAME)).thenReturn(Optional.of(TRAINER));

    service.addTrainerToTrainee(DTO, TRAINER_USERNAME);
    verify(traineeDao, times(1)).save(any());
  }

  @Test
  @DisplayName("getTrainersNotAssignedToTrainee() throws EntityNotFoundException when there is no trainee matching")
  void testCase10() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, ()-> service.getTrainersNotAssignedToTrainee(DTO));
    verify(trainerDao, times(0)).getTrainersNotAssignedToTrainee(any());
  }

  @Test
  @DisplayName("addTrainerToTrainee() adds trainer to trainee")
  void testCase11() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.of(TRAINEE));

    service.getTrainersNotAssignedToTrainee(DTO);
    verify(trainerDao, times(1)).getTrainersNotAssignedToTrainee(any());
  }

  @Test
  @DisplayName("getByUsername() throws not found exception")
  void testCase12() {
    when(trainerDao.getByUsername(DTO.getUsername())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getByUsername(DTO.getUsername()));
    verify(trainerDao, times(1)).getByUsername(any());
  }

  @Test
  @DisplayName("getByUsername() returns trainer")
  void testCase13() {
    when(trainerDao.getByUsername(DTO.getUsername())).thenReturn(Optional.of(TRAINER));

    service.getByUsername(DTO.getUsername());
    verify(trainerDao, times(1)).getByUsername(any());
  }

}