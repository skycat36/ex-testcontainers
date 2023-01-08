package org.example.testcontainers.repository.postgres;

import org.example.testcontainers.entity.postgres.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
  Optional<Car> findByNumber(Long number);

  void deleteByNumber(Long number);
}
