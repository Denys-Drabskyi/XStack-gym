package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.example.dao.TraineeDao;
import org.example.dao.TrainingDao;
import org.example.entity.Trainee;
import org.example.exception.EntityCreatingException;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.TraineeMapper;
import org.example.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TraineeServiceImplTest {

  @Mock
  private TraineeDao traineeDao;
  @Mock
  private TrainingDao trainingDao;
  @Mock
  private UserService userService;
  @Mock
  private TraineeMapper traineeMapper;
  private static final Trainee TRAINEE = new Trainee("firstname", "lastname", new Date(), "");

  @InjectMocks
  private TraineeServiceImpl service;

  @Test
  @DisplayName("getById() returns optional of Trainee")
  void testCase01() {
    when(traineeDao.get(any())).thenReturn(Optional.of(TRAINEE));

    assertEquals(TRAINEE, service.getById(UUID.randomUUID()).orElse(null));
  }

  @Test
  @DisplayName("getExistingById() throws EntityNotFoundException when there is no such trainee")
  void testCase02() {
    when(traineeDao.get(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getExistingById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getExistingById() returns trainee")
  void testCase03() {
    when(traineeDao.get(any())).thenReturn(Optional.of(TRAINEE));

    assertEquals(TRAINEE, service.getById(UUID.randomUUID()).orElse(null));
  }

  @Test
  @DisplayName("create(Trainee) throws EntityCreatingException when there is already user with this id")
  void testCase04() {
    when(userService.existsById(any())).thenReturn(true);

    assertThrows(EntityCreatingException.class, () -> service.create(TRAINEE));
    verify(userService, never()).checkForUsernameAvailable(any());
  }

  @Test
  @DisplayName("create() creates trainee")
  void testCase05() {
    when(userService.existsById(any())).thenReturn(false);
    when(traineeDao.save(TRAINEE)).thenReturn(TRAINEE);

    assertEquals(TRAINEE, service.create(TRAINEE));
    verify(userService, times(1)).checkForUsernameAvailable(any());
  }

  @Test
  @DisplayName("update() throws EntityNotFoundException when there is no such trainee")
  void testCase08() {
    when(traineeDao.get(TRAINEE.getId())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.update(TRAINEE)) ;
  }

  @Test
  @DisplayName("update() updates trainee")
  void testCase09() {
    when(traineeDao.get(TRAINEE.getId())).thenReturn(Optional.of(TRAINEE));

    service.update(TRAINEE);
    verify(traineeDao, times(1)).save(TRAINEE);
  }

  @Test
  @DisplayName("deleteById() ignores deletion chain if there already are no such trainee")
  void testCase11() {
    when(traineeDao.get(any())).thenReturn(Optional.empty());

    service.deleteById(UUID.randomUUID());
    verify(trainingDao, times(0)).deleteUserTrainings(any());
    verify(traineeDao, times(0)).delete(any());
  }

  @Test
  @DisplayName("deleteById() complete deletion chain")
  void testCase12() {
    when(traineeDao.get(any())).thenReturn(Optional.of(TRAINEE));

    service.deleteById(UUID.randomUUID());
    verify(trainingDao, times(1)).deleteUserTrainings(any());
    verify(traineeDao, times(1)).delete(any());
  }
}