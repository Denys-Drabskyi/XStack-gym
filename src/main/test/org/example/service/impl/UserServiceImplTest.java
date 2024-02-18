package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.example.dao.UserDao;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @Mock
  private UserDao userDao;
  @Mock
  private UserMapper userMapper;

  private static final User USER = new User("firstname", "lastname");

  @InjectMocks
  private UserServiceImpl service;

  @Test
  @DisplayName("getById() returns optional of user")
  void testCase01() {
    when(userDao.get(any())).thenReturn(Optional.of(USER));

    assertEquals(Optional.of(USER), service.getById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getExistingById() throws EntityNotFoundException when there is no such user")
  void testCase02() {
    when(userDao.get(any())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> service.getExistingById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("getExistingById() returns user")
  void testCase03() {
    when(userDao.get(any())).thenReturn(Optional.of(USER));

    assertEquals(USER, service.getExistingById(UUID.randomUUID()));
  }

  @Test
  @DisplayName("create(User) saves user without username changing when there is no other users with similar username")
  void testCase04() {
    User user = spy(USER);
    when(userDao.getLastWithUserNamePattern(any())).thenReturn(Optional.empty());

    service.create(user);
    verify(user, never()).addSuffixToUserNameAfter(any());
    verify(userDao, times(1)).save(user);
  }

  @Test
  @DisplayName("create(User) changes username when there is other users with similar username")
  void testCase05() {
    User user = spy(USER);
    when(userDao.getLastWithUserNamePattern(any())).thenReturn(Optional.of(USER));

    service.create(user);
    verify(user, times(1)).addSuffixToUserNameAfter(any());
    verify(userDao, times(1)).save(user);
  }

  @Test
  @DisplayName("create(UserDto) assert that calls create(User) after mapping")
  void testCase06() {
    UserService spy = spy(service);
    when(userMapper.newUserFromDto(any())).thenReturn(USER);

    spy.create(new UserDto());

    verify(spy, times(1)).create((User) any());

  }

  @Test
  @DisplayName("update(User) updates user")
  void testCase07() {
    UserService spy = spy(service);
    when(userDao.get(any())).thenReturn(Optional.of(USER));
    doNothing().when(userMapper).updateEntityFromEntity(any(), any());

    spy.update(USER);
    verify(spy, times(1)).getExistingById(any());
    verify(userDao, times(1)).save(USER);
  }

  @Test
  @DisplayName("update(UserDto) updates user")
  void testCase08() {
    UserService spy = spy(service);
    when(userDao.get(any())).thenReturn(Optional.of(USER));
    doNothing().when(userMapper).updateEntityFromEntity(any(), any());

    spy.update(USER);
    verify(spy, times(1)).getExistingById(any());
    verify(userDao, times(1)).save(USER);
  }

  @Test
  @DisplayName("deleteById() deletes user")
  void testCase09() {
    doNothing().when(userDao).delete(any());

    service.deleteById(any());
    verify(userDao, times(1)).delete(any());
  }
}