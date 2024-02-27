package org.example.dao;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Entity;
import org.example.exception.EntityNotFoundException;

@Slf4j
public abstract class BasicMapDao<ID, T extends Entity<ID>> implements CrudDao<T, ID> {
  protected Map<ID, T> storage;
  private final String entityName;

  public BasicMapDao(Map<ID, T> storage, String entityName) {
    this.storage = storage;
    this.entityName = entityName;
  }

  @Override
  public T save(T entity) {
    Objects.requireNonNull(entity, String.format("%s must not be null", entityName));
    log.info("Saving {} with id: {}", entityName, entity.getId());
    storage.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public Optional<T> get(ID id) {
    Objects.requireNonNull(id, "Id must not be null");
    log.info("Getting {} with id: {}", entityName, id);
    T entity = storage.get(id);
    if (entity == null){
      log.error("{} with id: {} does not exists", entityName, id);
    }
    return Optional.ofNullable(entity);
  }

//  @Override
//  public Boolean existById(ID id) {
//    log.info("Checking if {} exist by id: {}", entityName, id);
//    Objects.requireNonNull(id, "Id must not be null");
//    return storage.containsKey(id);
//  }

  @Override
  public void delete(ID id) {
    Objects.requireNonNull(id, "Id must not be null");
    log.info("Started {} with id: {} delete", entityName, id);
    if (!storage.containsKey(id)){
      log.error("Storage has no {} with id: {}", entityName, id);
      throw new EntityNotFoundException(String.format("Storage has no %s with id: %s", entityName, id));
    }
    storage.remove(id);
  }
}
