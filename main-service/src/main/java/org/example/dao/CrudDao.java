package org.example.dao;

import java.util.Optional;
import org.example.entity.IdEntity;

public interface CrudDao<T extends IdEntity<ID>, ID> {
  T save(T entity);

  Optional<T> getById(ID id);

  //  Optional<T> getById(ID id);
  boolean existById(ID id);
  void delete(ID id);
}
