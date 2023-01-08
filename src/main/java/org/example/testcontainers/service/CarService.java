package org.example.testcontainers.service;

import org.example.testcontainers.entity.postgres.Car;

public interface CarService {

  Car save(Car car);

  Car getByNumber(Long number);

  void deleteByNumber(Long number);
}
