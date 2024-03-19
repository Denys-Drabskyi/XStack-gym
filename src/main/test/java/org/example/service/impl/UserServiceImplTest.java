package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.example.dao.UserDao;
import org.example.dto.PasswordChangeDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @Mock
  private UserDao userDao;
  @Mock
  private UserMapper mapper;
  @InjectMocks
  private UserServiceImpl service;
  @Spy
  private User.UserBuilder userBuilder = User.builder();

  @Spy
  private User USER = User.builder().username("fname.lname").build();
  private static final User USER1 = User.builder().username("fname.lname1").build();
  private static final UserDto DTO = UserDto.builder()
      .firstName("fname")
      .lastName("lname")
      .username("test")
      .password("test")
      .build();
  private static final PasswordChangeDto PASSWORD_CHANGE_DTO = PasswordChangeDto.builder()
      .username("test.test")
      .password("oldPassword")
      .newPassword("newPassoword")
      .build();

  @Test
  @DisplayName("createUser() username, password generation test")
  void testCase01() {
    when(mapper.toBuilder(any())).thenReturn(userBuilder);
    when(userDao.getLastWithUserNamePattern(any())).thenReturn(Optional.empty());
    when(userDao.save(any())).thenReturn(USER);

    service.createUser(DTO);
    verify(userBuilder, times(1)).username("fname.lname");
    verify(userBuilder, times(1)).password(anyString());
    verify(userBuilder, times(1)).active(true);
  }

  @Test
  @DisplayName("createUser() username increment test")
  void testCase02() {
    userBuilder = mock(User.UserBuilder.class);
    when(mapper.toBuilder(any())).thenReturn(userBuilder);

    when(userBuilder.username(anyString())).thenReturn(userBuilder);
    when(userBuilder.password(anyString())).thenReturn(userBuilder);
    when(userBuilder.active(true)).thenReturn(userBuilder);
    when(userBuilder.build()).thenReturn(USER);
    when(userDao.getLastWithUserNamePattern(any())).thenReturn(Optional.of(USER1));

    when(userDao.save(any())).thenReturn(USER);

    service.createUser(DTO);
    verify(USER, times(1)).addSuffixToUserNameAfter(USER1.getUsername());
  }

  @Test
  @DisplayName("update() updates user test")
  void testCase03() {
    doNothing().when(mapper).updateEntityFromDto(any(), any());
    when(userDao.save(any())).thenReturn(USER);

    service.update(DTO, USER);
    verify(mapper, times(1)).updateEntityFromDto(DTO, USER);
    verify(userDao, times(1)).save(USER);
  }

  @Test
  @DisplayName("getByUsername() throws EntityNotFoundException when there is no such user")
  void testCase04() {
    when(userDao.getByUsername(anyString())).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, ()-> service.getByUsername(""));
  }

  @Test
  @DisplayName("getByUsername() returns user")
  void testCase05() {
    when(userDao.getByUsername(anyString())).thenReturn(Optional.of(USER));

    assertEquals(USER, service.getByUsername(""));
  }

  @Test
  @DisplayName("updatePassword() updates user password")
  void testCase06() {
    when(userDao.getByUsername(anyString())).thenReturn(Optional.of(USER));

    service.updatePassword(PASSWORD_CHANGE_DTO);
    verify(USER, times(1)).setPassword(PASSWORD_CHANGE_DTO.getNewPassword());
  }

  @Test
  @DisplayName("changeActive() true")
  void testCase07() {
    USER.setActive(true);
    when(userDao.getByUsername(anyString())).thenReturn(Optional.of(USER));

    service.changeActive(PASSWORD_CHANGE_DTO);
    verify(USER, times(1)).setActive(false);
    verify(userDao, times(1)).save(USER);
  }

  @Test
  @DisplayName("changeActive() activates user")
  void testCase08() {
    USER.setActive(false);
    when(userDao.getByUsername(anyString())).thenReturn(Optional.of(USER));

    service.changeActive(PASSWORD_CHANGE_DTO);
    verify(USER, times(1)).setActive(true);
    verify(userDao, times(1)).save(USER);
  }

  @Test
  @DisplayName("existsById() returns dao result")
  void testCase13() {
    when(userDao.existById(any())).thenReturn(true);

    assertTrue(service.existsById(UUID.randomUUID()));
    verify(userDao, times(1)).existById(any());
  }

  @Test
  @DisplayName("auth() returns dao result")
  void testCase14() {
    when(userDao.existsByUsernameAndPasswordAndActive(DTO.getUsername(), DTO.getPassword(), true)).thenReturn(true);

    assertTrue(service.auth(DTO));
    verify(userDao, times(1)).existsByUsernameAndPasswordAndActive(DTO.getUsername(), DTO.getPassword(), true);
  }
}