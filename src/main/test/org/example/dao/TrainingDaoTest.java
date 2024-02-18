package org.example.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.example.entity.Training;
import org.example.entity.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrainingDaoTest {
  private Map<UUID, Training> map;
  private TrainingDao dao;
  private static final UUID TEST_UUID = UUID.fromString("bdb48c40-c082-4038-82fa-c81cd1fc4270");

  private static final Training TRAINING_1 = new Training(
      TEST_UUID,
      UUID.randomUUID(),
      "",
      TrainingType.TYPE_1,
      new Date(),
      0L
  );

  private static final Training TRAINING_2 = new Training(
      UUID.randomUUID(),
      TEST_UUID,
      "",
      TrainingType.TYPE_1,
      new Date(),
      0L
  );

  private static final Training TRAINING_3 = new Training(
      UUID.randomUUID(),
      UUID.randomUUID(),
      "",
      TrainingType.TYPE_1,
      new Date(),
      0L
  );

  @BeforeEach
  void setUp() {
    map = new HashMap<>();
    dao = new TrainingDao(map);
  }

  @Test
  @DisplayName("deleteUserTrainings() should delete all trainings where traineeId or trainerId equals to argument")
  void testCase01() {
    map.put(TRAINING_1.getId(), TRAINING_1);
    map.put(TRAINING_2.getId(), TRAINING_2);
    map.put(TRAINING_3.getId(), TRAINING_3);

    dao.deleteUserTrainings(TEST_UUID);
    assertEquals(1, map.keySet().size());
  }


}