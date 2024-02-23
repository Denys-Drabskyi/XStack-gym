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
import org.example.entity.Trainer;
import org.example.exception.EntityCreatingException;
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

  private static final Trainer TRAINER = Trainer.builder().build();
//      new Trainer("firstname", "lastname", TrainingType.TYPE_1);

  @InjectMocks
  private TrainerServiceImpl service;

  @Test
  @DisplayName("getById() returns optional of Trainer")
  void testCase01() {
    when(trainerDao.get(any())).thenReturn(Optional.of(TRAINER));

    assertEquals(TRAINER, service.getById(UUID.randomUUID()).orElse(null));
  }

  @Test
  @DisplayName("getExistingById() throws EntityNotFoundException when there is no such trainer")
  void testCase02() {
    when(trainerDao.get(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getExistingById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getExistingById() returns trainer")
  void testCase03() {
    when(trainerDao.get(any())).thenReturn(Optional.of(TRAINER));

    assertEquals(TRAINER, service.getById(UUID.randomUUID()).orElse(null));
  }

  @Test
  @DisplayName("create() throws EntityCreatingException when there is already user with this id")
  void testCase04() {
    when(userService.existsById(any())).thenReturn(true);

    assertThrows(EntityCreatingException.class, () -> service.create(TRAINER));
    verify(userService, never()).checkForUsernameAvailable(any());
  }

  @Test
  @DisplayName("create() creates trainer")
  void testCase05() {
    when(userService.existsById(any())).thenReturn(false);
    when(trainerDao.save(any())).thenReturn(TRAINER);
    when(trainerMapper.toBuilder(TRAINER)).thenReturn(Trainer.builder());

    service.create(TRAINER);
    verify(userService, times(1)).checkForUsernameAvailable(any());
    verify(trainerMapper, times(1)).toBuilder(TRAINER);
    verify(trainerDao, times(1)).save(any());
  }

  @Test
  @DisplayName("update() throws EntityNotFoundException when there is no such trainer")
  void testCase08() {
    when(trainerDao.get(TRAINER.getId())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.update(TRAINER)) ;
  }

  @Test
  @DisplayName("update() updates trainer")
  void testCase09() {
    when(trainerDao.get(TRAINER.getId())).thenReturn(Optional.of(TRAINER));

    service.update(TRAINER);
    verify(trainerDao, times(1)).save(TRAINER);
  }

}