package org.example.entity;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TrainingType {
  TYPE_1("type1", UUID.fromString("25c49575-a9eb-483f-b3bd-a35a39d6849b")),
  TYPE_2("type2", UUID.fromString("25c49575-a9eb-483f-b3bd-a35a39d6849c")),
  TYPE_3("type3", UUID.fromString("25c49575-a9eb-483f-b3bd-a35a39d6849d"));

  private final String name;
  private final UUID id;
}
