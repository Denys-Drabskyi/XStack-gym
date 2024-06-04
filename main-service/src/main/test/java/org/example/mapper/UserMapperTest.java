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

  private final User user = User.builder()
      .id(UUID.randomUUID())
      .username("username")
      .password("password")
      .firstName("firstname")
      .lastName("lastname")
      .password("password")
      .active(true)
      .build();

  private final UserDto dto = UserDto.builder()
      .username("newUsername")
      .password("newPassword")
      .firstName("newFirstname")
      .lastName("newLastname")
      .password("newPassword")
      .active(false)
      .build();


  @Test
  @DisplayName("toBuilder() test")
  void testCase01() {
    User rez = mapper.toBuilder(dto).build();

    assertEquals(dto.getFirstName(), rez.getFirstName());
    assertEquals(dto.getLastName(), rez.getLastName());
    assertEquals(dto.getUsername(), rez.getUsername());
    assertEquals(dto.getPassword(), rez.getPassword());
    assertEquals(dto.isActive(), rez.isActive());
  }

  @Test
  @DisplayName("toBuilder() test")
  void testCase02() {
    mapper.updateEntityFromDto(dto, user);

    assertEquals(dto.getFirstName(), user.getFirstName());
    assertEquals(dto.getLastName(), user.getLastName());
    assertEquals(dto.getPassword(), user.getPassword());

    assertNotEquals(dto.getUsername(), user.getUsername());
    assertNotEquals(dto.isActive(), user.isActive());
  }
}