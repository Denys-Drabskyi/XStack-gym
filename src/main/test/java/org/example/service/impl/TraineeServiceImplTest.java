package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.example.dao.TraineeDao;
import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
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
  private UserService userService;
  @Mock
  private TraineeMapper traineeMapper;
  private static final Trainee TRAINEE = Trainee.builder().build();
  private static final TraineeDto DTO = TraineeDto.builder().build();
  private static final Trainee.TraineeBuilder BUILDER = Trainee.builder();

  @InjectMocks
  private TraineeServiceImpl service;

  @Test
  @DisplayName("existsById() returns optional of Trainee")
  void testCase01() {
    when(traineeDao.existById(any())).thenReturn(true);

    assertTrue(service.existsById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("get() throws EntityNotFoundException when there is no such trainee")
  void testCase02() {
    when(traineeDao.getByUsername(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.get(DTO));
  }

  @Test
  @DisplayName("getExistingById() returns trainee")
  void testCase03() {
    when(traineeDao.getByUsername(any())).thenReturn(Optional.of(TRAINEE));
    when(traineeMapper.toDto(TRAINEE)).thenReturn(DTO);

    assertEquals(DTO, service.get(DTO));
  }

  @Test
  @DisplayName("create() creates user")
  void testCase04() {
    when(userService.createUser(DTO)).thenReturn(null);
    when(traineeMapper.toBuilder(DTO)).thenReturn(BUILDER);
    when(traineeDao.save(any())).thenReturn(TRAINEE);
    when(traineeMapper.toDto(TRAINEE)).thenReturn(DTO);

    assertEquals(DTO, service.create(DTO));
  }

  @Test
  @DisplayName("update() throws EntityNotFoundException when there is no such trainee")
  void testCase05() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.update(DTO)) ;
  }

  @Test
  @DisplayName("update() updates trainee")
  void testCase06() {
    when(traineeDao.getByUsername(DTO.getUsername())).thenReturn(Optional.of(TRAINEE));

    service.update(DTO);
    verify(traineeDao, times(1)).save(TRAINEE);
  }

  @Test
  @DisplayName("deleteById() does nothing if there already are no such trainee")
  void testCase07() {
    when(traineeDao.getByUsername(any())).thenReturn(Optional.empty());

    service.deleteByUsername(DTO);
    verify(traineeDao, times(0)).delete(any());
  }

  @Test
  @DisplayName("deleteById() delete trainee")
  void testCase12() {
    when(traineeDao.getByUsername(any())).thenReturn(Optional.of(TRAINEE));

    service.deleteByUsername(DTO);
    verify(traineeDao, times(1)).delete(any());
  }

  @Test
  @DisplayName("getByUsername() delete trainee")
  void testCase13() {
    when(traineeDao.getByUsername(any())).thenReturn(Optional.of(TRAINEE));

    service.getWithTrainers("username");
    verify(traineeDao, times(1)).getByUsername(any());
  }
}