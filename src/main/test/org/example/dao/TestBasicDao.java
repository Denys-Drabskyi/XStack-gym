package org.example.dao;

import java.util.Map;

public class TestBasicDao extends BasicMapDao<Integer, TestEntity> {
  public TestBasicDao(Map<Integer, TestEntity> storage, String entityName) {
    super(storage, entityName);
  }
}
