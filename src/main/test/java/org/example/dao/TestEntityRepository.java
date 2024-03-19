package org.example.dao;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestIdEntity, UUID> {
}
