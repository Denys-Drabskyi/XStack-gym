package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;
import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.example.util.PasswordGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TraineeMapperTest {
  private final TraineeMapperImpl mapper = new TraineeMapperImpl();
  private static final User USER = User.builder()
      .id(UUID.randomUUID())
      .firstName("firstname")
      .lastName("lastname")
      .username("firstname.lastname")
      .password(PasswordGenerator.generatePassword())
      .active(true)
      .build();
  private static final Trainee TRAINEE = Trainee.builder()
      .id(UUID.randomUUID())
      .user(USER)
      .birthDate(LocalDate.of(2000,1,1))
      .address("address")
      .build();

  private static final TraineeDto TRAINEE_DTO = TraineeDto.builder()
      .id(UUID.randomUUID())
      .firstName("not firstname")
      .lastName("not lastName")
      .username("not firstName.lastName")
      .password(PasswordGenerator.generatePassword())
      .active(false)
      .birthDate(LocalDate.of(2001,1,1))
      .address("now address")
      .build();

  @Test
  @DisplayName("updateEntityFromDto() test")
  void testCase01() {
    mapper.updateEntityFromDto(TRAINEE_DTO, TRAINEE);

    assertEquals(TRAINEE.getBirthDate(),            TRAINEE_DTO.getBirthDate());
    assertEquals(TRAINEE.getAddress(),              TRAINEE_DTO.getAddress());

    assertNotEquals(TRAINEE.getId(),                TRAINEE_DTO.getId());
  }

  @Test
  @DisplayName("toDto() test")
  void testCase02() {
    var rez = mapper.toDto(TRAINEE);

    assertEquals(TRAINEE.getUser().getFirstName(),  rez.getFirstName());
    assertEquals(TRAINEE.getUser().getLastName(),   rez.getLastName());
    assertEquals(TRAINEE.getUser().isActive(),      rez.isActive());
    assertEquals(TRAINEE.getBirthDate(),            rez.getBirthDate());
    assertEquals(TRAINEE.getAddress(),              rez.getAddress());
    assertEquals(TRAINEE.getUser().getUsername(),   rez.getUsername());
    assertEquals(TRAINEE.getId(),                   rez.getId());
  }

  @Test
  @DisplayName("toBuilder() test")
  void testCase03() {

    var rez = mapper.toBuilder(TRAINEE_DTO).build();

    assertEquals(TRAINEE.getBirthDate(), rez.getBirthDate());
    assertEquals(TRAINEE.getAddress(),   rez.getAddress());

    assertNotEquals(TRAINEE.getId(),     rez.getId());
    assertNull(rez.getUser());
  }
}