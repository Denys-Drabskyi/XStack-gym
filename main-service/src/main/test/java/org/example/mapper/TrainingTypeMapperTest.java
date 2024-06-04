package org.example.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.example.entity.TrainingType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrainingTypeMapperTest {
  private final TrainingTypeMapper mapper = new TrainingTypeMapper();

  @Test
  @DisplayName("toName test")
  void testCase01() {
    TrainingType trainingType = new TrainingType();
    trainingType.setName("Strength Training");

    String result = mapper.toName(trainingType);

    assertEquals("Strength Training", result);
  }

  @Test
  @DisplayName("toType test")
  void testCase02() {
    String name = "Cardio";

    TrainingType result = mapper.toType(name);

    assertEquals(name, result.getName());
  }

  @Test
  @DisplayName("toNames test")
  void testCase03() {
    List<TrainingType> trainingTypes = Arrays.asList(
        new TrainingType(UUID.randomUUID(), "Yoga"),
        new TrainingType(UUID.randomUUID(), "Pilates"),
        new TrainingType(UUID.randomUUID(), "CrossFit")
    );

    List<String> result = mapper.toNames(trainingTypes);

    assertEquals(Arrays.asList("Yoga", "Pilates", "CrossFit"), result);
  }

  @Test
  @DisplayName("toTypes test")
  void testCase04() {
    List<String> names = Arrays.asList("Running", "Cycling", "Swimming");

    List<TrainingType> result = mapper.toTypes(names);

    assertEquals(3, result.size());
    assertEquals("Running", result.get(0).getName());
    assertEquals("Cycling", result.get(1).getName());
    assertEquals("Swimming", result.get(2).getName());
  }
}