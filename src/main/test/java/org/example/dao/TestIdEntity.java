package org.example.dao;

import java.util.UUID;
import org.example.entity.IdEntity;

public record TestIdEntity(UUID id) implements IdEntity<UUID> {

  @Override
  public UUID getId() {
    return id;
  }
}
