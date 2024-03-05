package org.example.dao;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
@RequiredArgsConstructor
public abstract class BasicDao<ID, T extends IdEntity<ID>, R extends JpaRepository<T, ID>> implements CrudDao<T, ID> {
  protected final R repository;
  private final String entityName;

  @Override
  public T save(T entity) {
    Objects.requireNonNull(entity, String.format("%s must not be null", entityName));
    log.info("Saving {} with id: {}", entityName, entity.getId());
    return repository.save(entity);
  }

  @Override
  public Optional<T> getById(ID id) {
    Objects.requireNonNull(id, "Id must not be null");
    log.info("Getting {} with id: {}", entityName, id);
    return repository.findById(id);
  }

  @Override
  public boolean existById(ID id) {
    log.info("Checking if {} exist by id: {}", entityName, id);
    Objects.requireNonNull(id, "Id must not be null");
    return repository.existsById(id);
  }

  @Override
  public void delete(ID id) {
    Objects.requireNonNull(id, "Id must not be null");
    log.info("Started {} with id: {} delete", entityName, id);
    repository.deleteById(id);
  }
}
