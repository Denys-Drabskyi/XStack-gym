package org.example.dao;

import org.example.entity.Entity;

public record TestEntity(Integer id) implements Entity<Integer> {

  @Override
  public Integer getId() {
    return id;
  }
}
