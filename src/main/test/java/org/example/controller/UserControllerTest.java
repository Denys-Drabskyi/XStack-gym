package org.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.dto.PasswordChangeDto;
import org.example.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @Mock
  private UserService service;
  @InjectMocks
  private UserController controller;

  PasswordChangeDto dto = new PasswordChangeDto();

  @Test
  @DisplayName("login calls service")
  void testCase01() {
    when(service.auth(any())).thenReturn(true);
    controller.login(dto);

    verify(service, times(1)).auth(dto);
  }

  @Test
  @DisplayName("passwordChange calls service")
  void testCase02() {
    doNothing().when(service).updatePassword(any());
    controller.passwordChange(dto);

    verify(service, times(1)).updatePassword(dto);
  }

  @Test
  @DisplayName("activateDeactivateUser calls service")
  void testCase03() {
    doNothing().when(service).changeActive(any());
    controller.activateDeactivateUser(dto);

    verify(service, times(1)).changeActive(dto);
  }
}