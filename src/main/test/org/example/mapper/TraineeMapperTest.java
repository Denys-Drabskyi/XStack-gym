package org.example.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.example.entity.Trainee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TraineeMapperTest {
  private final TraineeMapperImpl mapper = new TraineeMapperImpl();
  private static final Trainee TRAINEE = new Trainee("firstName", "lastName", new Date(), "");

  @Test
  @DisplayName("updateEntityFromEntity() test")
  void testCase04() {
    Trainee rez = new Trainee("firstName1", "lastName1", new Date(), "newAdress");
    mapper.updateEntityFromEntity(TRAINEE, rez);

    assertEquals(TRAINEE.getFirstName(), rez.getFirstName());
    assertEquals(TRAINEE.getLastName(), rez.getLastName());
    assertEquals(TRAINEE.isActive(), rez.isActive());
    assertEquals(TRAINEE.getBirthDate(), rez.getBirthDate());
    assertEquals(TRAINEE.getAddress(), rez.getAddress());

    assertNotEquals(TRAINEE.getUsername(), rez.getUsername());
  }
}