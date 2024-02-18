package org.example.dao;

import java.util.Optional;
import org.example.entity.Entity;

public interface CrudDao<T extends Entity<ID>, ID> {
  T save(T entity);
  Optional<T> get(ID id);
//  Boolean existById(ID id);
  void delete(ID id);
}
