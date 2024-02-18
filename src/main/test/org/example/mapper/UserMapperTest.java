package org.example.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.UUID;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserMapperTest {
  private final UserMapperImpl mapper = new UserMapperImpl();
  private static final UserDto USER_DTO = new UserDto(
      UUID.randomUUID(),
      "firstname",
      "lastname",
      "username",
      "password",
      true);
  private static final User USER = new User("firstname", "lastname");

  @Test
  @DisplayName("newUserFromDto() test")
  void testCase01() {
    User rez = mapper.newUserFromDto(USER_DTO);
    assertEquals(USER_DTO.getFirstName(), rez.getFirstName());
    assertEquals(USER_DTO.getLastName(), rez.getLastName());

    assertNotEquals(USER_DTO.getUsername(), rez.getUsername());
    assertNotEquals(USER_DTO.getPassword(), rez.getPassword());
    assertNotEquals(USER_DTO.getUserId(), rez.getId());
  }

  @Test
  @DisplayName("updateEntityFromDto() test")
  void testCase02() {
    User rez = new User("newFirstName", "newLastName");
    mapper.updateEntityFromDto(USER_DTO, rez);
    assertEquals(USER_DTO.getFirstName(), rez.getFirstName());
    assertEquals(USER_DTO.getLastName(), rez.getLastName());
    assertEquals(USER_DTO.getPassword(), rez.getPassword());
    assertEquals(USER_DTO.isActive(), rez.isActive());

    assertNotEquals(USER_DTO.getUsername(), rez.getUsername());
    assertNotEquals(USER_DTO.getUserId(), rez.getId());
  }

  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase03() {
    User rez = new User("newFirstName", "newLastName");
    mapper.updateEntityFromEntity(USER, rez);
    assertEquals(USER.getFirstName(), rez.getFirstName());
    assertEquals(USER.getLastName(), rez.getLastName());
    assertEquals(USER.getPassword(), rez.getPassword());
    assertEquals(USER.isActive(), rez.isActive());

    assertNotEquals(USER.getUsername(), rez.getUsername());
    assertNotEquals(USER.getId(), rez.getId());
  }
}