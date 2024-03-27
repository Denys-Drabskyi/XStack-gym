package org.example.dao;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BasicDaoTest {
  @Mock
  private TestEntityRepository repository;
  @InjectMocks
  private TestBasicDao dao;

  private final TestIdEntity entity = new TestIdEntity(UUID.randomUUID());


  @Test
  @DisplayName("save() without argument should throw NullPointerException")
  void testCase01() {
    assertThrows(NullPointerException.class, () -> dao.save(null));
  }

  @Test
  @DisplayName("save() with argument should save it")
  void testCase02() {
    when(repository.save(entity)).thenReturn(entity);
    dao.save(entity);
    verify(repository, times(1)).save(any());
  }

  @Test
  @DisplayName("getById() without argument should throw NullPointerException")
  void testCase03() {
    assertThrows(NullPointerException.class, () -> dao.getById(null));
  }

  @Test
  @DisplayName("getById() should return empty optional, if entity is not present")
  void testCase04() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    assertTrue(dao.getById(UUID.randomUUID()).isEmpty());
  }

  @Test
  @DisplayName("get() should return optional of entity, if it is present")
  void testCase05() {
    when(repository.findById(any())).thenReturn(Optional.of(entity));
    assertTrue(dao.getById(UUID.randomUUID()).isPresent());
  }

  @Test
  @DisplayName("delete() without argument should throw NullPointerException")
  void testCase06() {
    assertThrows(NullPointerException.class, () -> dao.delete(null));
  }

  @Test
  @DisplayName("delete() should call repository delete method")
  void testCase07() {
    doNothing().when(repository).deleteById(any());
    dao.delete(UUID.randomUUID());
    verify(repository, times(1)).deleteById(any());
  }

  @Test
  @DisplayName("existById() without argument should throw NullPointerException")
  void testCase08() {
    assertThrows(NullPointerException.class, () -> dao.existById(null));
  }

  @Test
  @DisplayName("existById() should return result of repository.existsById")
  void testCase09() {
    when(repository.existsById(any())).thenReturn(true);
    assertTrue(dao.existById(UUID.randomUUID()));
  }

}
