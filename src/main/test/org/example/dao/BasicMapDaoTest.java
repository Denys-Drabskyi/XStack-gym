package org.example.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.example.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasicMapDaoTest {
  private Map<Integer, TestEntity> map;
  private BasicMapDao<Integer, TestEntity> dao;

  @BeforeEach
  void setUp() {
    map = new HashMap<>();
    dao = new TestBasicDao(map, "test");
  }

  @Test
  @DisplayName("save() without argument should throw NullPointerException")
  void testCase01() {
    assertThrows(NullPointerException.class, () -> dao.save(null));
  }

  @Test
  @DisplayName("save() with argument should save it")
  void testCase02() {
    dao.save(new TestEntity(1));

    assertEquals(1, map.keySet().size());
    assertTrue(map.containsKey(1));
  }

  @Test
  @DisplayName("get() without argument should throw NullPointerException")
  void testCase03() {
    assertThrows(NullPointerException.class, () -> dao.get(null));
  }

  @Test
  @DisplayName("get() should return empty optional, if entity is not present")
  void testCase04() {
    assertTrue(dao.get(1).isEmpty());
  }

  @Test
  @DisplayName("get() should return optional of entity, if it is present")
  void testCase05() {
    map.put(1, new TestEntity(1));
    assertTrue(dao.get(1).isPresent());
  }

  @Test
  @DisplayName("delete() without argument should throw NullPointerException")
  void testCase06() {
    assertThrows(NullPointerException.class, () -> dao.delete(null));
  }

  @Test
  @DisplayName("delete() should throw EntityNotFoundException, if entity is not present")
  void testCase07() {
    assertThrows(EntityNotFoundException.class, () -> dao.delete(1));
  }

  @Test
  @DisplayName("delete() should delete entity, if it is present")
  void testCase08() {
    map.put(1, new TestEntity(1));
    dao.delete(1);
    assertEquals(0, map.keySet().size());
  }

}
