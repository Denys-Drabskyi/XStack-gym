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
import org.example.dto.TraineeDto;
import org.example.dto.UserDto;
import org.example.entity.Trainee;
import org.example.entity.User;
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

  private static final User USER = new User("firstname", "lastname");

  private static final Trainee TRAINEE = new Trainee(new Date(), "", USER.getId());

  @InjectMocks
  private TraineeServiceImpl service;

  @Test
  @DisplayName("getById() throws EntityNotFoundException when there is no such trainee")
  void testCase01() {
    when(traineeDao.get(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getById() throws EntityNotFoundException when there is no such user relayed to trainee")
  void testCase02() {
    when(traineeDao.get(any())).thenReturn(Optional.of(TRAINEE));
    when(userService.getExistingById(USER.getId()))
        .thenThrow(EntityNotFoundException.byId(USER.getId(), ""));

    assertThrows(EntityNotFoundException.class, () -> service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getById() returns TraineeDto")
  void testCase03() {
    when(traineeDao.get(any())).thenReturn(Optional.of(TRAINEE));
    when(userService.getExistingById(USER.getId())).thenReturn(USER);
    when(traineeMapper.fromUser(USER)).thenReturn(new TraineeDto());

    assertEquals(TraineeDto.class, service.getById(UUID.randomUUID()).getClass());
  }

  @Test
  @DisplayName("create(Trainee) throws EntityNotFoundException when there is no user with id like in trainee.userid")
  void testCase04() {
    when(userService.getById(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.create(TRAINEE));
  }

  @Test
  @DisplayName("create(Trainee) creates trainee")
  void testCase05() {
    when(userService.getById(any())).thenReturn(Optional.of(USER));

    service.create(TRAINEE);
    verify(traineeDao, times(1)).save(TRAINEE);
  }

  @Test
  @DisplayName("create(TraineeDto) creates trainee when there is no user with id like in traineeDto.userid")
  void testCase06() {
    TraineeDto traineeDto = new TraineeDto();
    when(userService.getById(any())).thenReturn(Optional.empty());
    when(userService.create(traineeDto)).thenReturn(USER);
    when(traineeMapper.newTraineeFromDto(traineeDto)).thenReturn(TRAINEE);
    when(traineeMapper.fromUser(USER)).thenReturn(traineeDto);

    assertEquals(TraineeDto.class, service.create(traineeDto).getClass()) ;
    verify(userService, times(1)).create(traineeDto);
  }

  @Test
  @DisplayName("create(TraineeDto) creates trainee when there is user with id like in traineeDto.userid")
  void testCase07() {
    TraineeDto traineeDto = new TraineeDto();
    when(userService.getById(any())).thenReturn(Optional.of(USER));
    when(traineeMapper.newTraineeFromDto(traineeDto)).thenReturn(TRAINEE);
    when(traineeMapper.fromUser(USER)).thenReturn(traineeDto);

    assertEquals(TraineeDto.class, service.create(traineeDto).getClass());
    verify(userService, never()).create(traineeDto);
  }

  @Test
  @DisplayName("update(Trainee) throws EntityNotFoundException when there is no such trainee")
  void testCase08() {
    when(traineeDao.get(TRAINEE.getId())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.update(TRAINEE)) ;
  }

  @Test
  @DisplayName("update(Trainee) updates trainee")
  void testCase09() {
    when(traineeDao.get(TRAINEE.getId())).thenReturn(Optional.of(TRAINEE));

    service.update(TRAINEE);
    verify(traineeDao, times(1)).save(TRAINEE);
  }

  @Test
  @DisplayName("update(TraineeDto) updates trainee")
  void testCase10() {
    when(userService.update((UserDto) any())).thenReturn(USER);
    when(traineeMapper.newTraineeFromDto(any())).thenReturn(TRAINEE);
    when(traineeDao.get(TRAINEE.getId())).thenReturn(Optional.of(TRAINEE));

    service.update(new TraineeDto());
    verify(traineeDao, times(1)).save(TRAINEE);
  }

  @Test
  @DisplayName("deleteById() ignores deletion chain if there already are no such trainee")
  void testCase11() {
    when(traineeDao.get(any())).thenReturn(Optional.empty());

    service.deleteById(UUID.randomUUID());
    verify(trainingDao, times(0)).deleteUserTrainings(any());
    verify(traineeDao, times(0)).delete(any());
    verify(userService, times(0)).deleteById(any());
  }

  @Test
  @DisplayName("deleteById() complete deletion chain")
  void testCase12() {
    when(traineeDao.get(any())).thenReturn(Optional.of(TRAINEE));

    service.deleteById(UUID.randomUUID());
    verify(trainingDao, times(1)).deleteUserTrainings(any());
    verify(traineeDao, times(1)).delete(any());
    verify(userService, times(1)).deleteById(any());
  }
}