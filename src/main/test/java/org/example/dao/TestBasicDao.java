package org.example.dao;

import java.util.UUID;

public class TestBasicDao extends BasicDao<UUID, TestIdEntity, TestEntityRepository> {

  public TestBasicDao(TestEntityRepository repository, String entityName) {
    super(repository, entityName);
  }
}
