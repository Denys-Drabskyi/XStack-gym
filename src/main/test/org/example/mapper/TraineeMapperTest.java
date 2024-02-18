package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.UUID;
import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TraineeMapperTest {
  private final TraineeMapperImpl mapper = new TraineeMapperImpl();
  private static final User USER = new User("firstname", "lastname");
  private static final TraineeDto TRAINEE_DTO = new TraineeDto(UUID.randomUUID(), new Date(), "address");
  private static final Trainee TRAINEE = new Trainee(new Date(), "", UUID.randomUUID());


  @Test
  @DisplayName("fromUser() test")
  void testCase01() {
    TraineeDto rez = mapper.fromUser(USER);
    assertNull(rez.getUserId());
    assertEquals(USER.getFirstName(), rez.getFirstName());
    assertEquals(USER.getLastName(), rez.getLastName());
    assertEquals(USER.getUsername(), rez.getUsername());
    assertEquals(USER.getPassword(), rez.getPassword());
    assertEquals(USER.isActive(), rez.isActive());
  }

  @Test
  @DisplayName("newTraineeFromDto() test")
  void testCase02() {
    TRAINEE_DTO.setUserId(UUID.randomUUID());
    Trainee rez = mapper.newTraineeFromDto(TRAINEE_DTO);
    assertEquals(TRAINEE_DTO.getUserId(), rez.getUserId());
    assertEquals(TRAINEE_DTO.getBirthDate(), rez.getBirthDate());
    assertEquals(TRAINEE_DTO.getAddress(), rez.getAddress());
  }

  @Test
  @DisplayName("updateDtoFromEntity() test")
  void testCase03() {
    TraineeDto rez = new TraineeDto();
    mapper.updateDtoFromEntity(TRAINEE, rez);
    assertEquals(TRAINEE.getUserId(), rez.getUserId());
    assertEquals(TRAINEE.getBirthDate(), rez.getBirthDate());
    assertEquals(TRAINEE.getAddress(), rez.getAddress());
    assertEquals(TRAINEE.getId(), rez.getId());
  }

  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase04() {
    Trainee rez = new Trainee(new Date(), "newAdress", UUID.randomUUID());
    mapper.updateEntityFromEntity(TRAINEE, rez);
//    because we cannot update userId
    assertNotEquals(TRAINEE.getUserId(), rez.getUserId());
    assertEquals(TRAINEE.getBirthDate(), rez.getBirthDate());
    assertEquals(TRAINEE.getAddress(), rez.getAddress());
  }
}