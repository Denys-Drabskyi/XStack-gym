package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.example.dao.TrainerDao;
import org.example.dto.TrainerDto;
import org.example.dto.UserDto;
import org.example.entity.Trainer;
import org.example.entity.TrainingType;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.TrainerMapper;
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
  private UserService userService;
  @Mock
  private TrainerMapper trainerMapper;

  private static final User USER = new User("firstname", "lastname");

  private static final Trainer TRAINER = new Trainer(TrainingType.TYPE_1, USER.getId());

  @InjectMocks
  private TrainerServiceImpl service;

  @Test
  @DisplayName("getById() throws EntityNotFoundException when there is no such trainer")
  void testCase01() {
    when(trainerDao.get(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getById() throws EntityNotFoundException when there is no such user relayed to trainer")
  void testCase02() {
    when(trainerDao.get(any())).thenReturn(Optional.of(TRAINER));
    when(userService.getExistingById(USER.getId()))
        .thenThrow(EntityNotFoundException.byId(USER.getId(), ""));

    assertThrows(EntityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getById() returns TraineeDto")
  void testCase03() {
    when(trainerDao.get(any())).thenReturn(Optional.of(TRAINER));
    when(userService.getExistingById(USER.getId())).thenReturn(USER);
    when(trainerMapper.fromUser(USER)).thenReturn(new TrainerDto());

    assertEquals(TrainerDto.class, service.getById(UUID.randomUUID()).getClass());
  }

  @Test
  @DisplayName("create(Trainer) throws EntityNotFoundException when there is no user with id like in trainer.userid")
  void testCase04() {
    when(userService.getById(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.create(TRAINER));
  }

  @Test
  @DisplayName("create(Trainer) creates trainer")
  void testCase05() {
    when(userService.getById(any())).thenReturn(Optional.of(USER));

    service.create(TRAINER);
    verify(trainerDao, times(1)).save(TRAINER);
  }

  @Test
  @DisplayName("create(TrainerDto) creates trainee when there is no user with id like in trainerDto.userid")
  void testCase06() {
    TrainerDto trainerDto = new TrainerDto();
    when(userService.getById(any())).thenReturn(Optional.empty());
    when(userService.create(trainerDto)).thenReturn(USER);
    when(trainerMapper.newTrainerFromDto(trainerDto)).thenReturn(TRAINER);
    when(trainerMapper.fromUser(USER)).thenReturn(trainerDto);

    assertEquals(TrainerDto.class, service.create(trainerDto).getClass()) ;
    verify(userService, times(1)).create(trainerDto);
  }

  @Test
  @DisplayName("create(TrainerDto) creates trainer when there is user with id like in trainerDto.userid")
  void testCase07() {
    TrainerDto trainerDto = new TrainerDto();
    when(userService.getById(any())).thenReturn(Optional.of(USER));
    when(trainerMapper.newTrainerFromDto(trainerDto)).thenReturn(TRAINER);
    when(trainerMapper.fromUser(USER)).thenReturn(trainerDto);

    assertEquals(TrainerDto.class, service.create(trainerDto).getClass());
    verify(userService, never()).create(trainerDto);
  }

  @Test
  @DisplayName("update(Trainer) throws EntityNotFoundException when there is no such trainer")
  void testCase08() {
    when(trainerDao.get(TRAINER.getId())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.update(TRAINER)) ;
  }

  @Test
  @DisplayName("update(Trainer) updates trainer")
  void testCase09() {
    when(trainerDao.get(TRAINER.getId())).thenReturn(Optional.of(TRAINER));

    service.update(TRAINER);
    verify(trainerDao, times(1)).save(TRAINER);
  }

  @Test
  @DisplayName("update(TrainerDto) updates trainer")
  void testCase10() {
    when(userService.update((UserDto) any())).thenReturn(USER);
    when(trainerMapper.newTrainerFromDto(any())).thenReturn(TRAINER);
    when(trainerDao.get(TRAINER.getId())).thenReturn(Optional.of(TRAINER));

    service.update(new TrainerDto());
    verify(trainerDao, times(1)).save(TRAINER);
  }
}