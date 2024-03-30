package org.example.dao;

import java.util.Objects;
import java.util.UUID;
import org.example.entity.IdEntity;

public final class TestIdEntity implements IdEntity<UUID> {
  private final UUID id;

  public TestIdEntity(UUID id) {
    this.id = id;
  }

  @Override
  public UUID getId() {
    return id;
  }

  public UUID id() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (TestIdEntity) obj;
    return Objects.equals(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "TestIdEntity[" +
        "id=" + id + ']';
  }

}
