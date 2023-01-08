package org.example.testcontainers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.entity.postgres.Car;
import org.example.testcontainers.repository.postgres.CarRepository;
import org.example.testcontainers.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;

  @Transactional
  @Override
  public Car save(Car car) {
    return carRepository.save(car);
  }

  @Override
  public Car getByNumber(Long number) {
    return carRepository.findByNumber(number)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Car with number not exist"));
  }

  @Transactional
  @Override
  public void deleteByNumber(Long number) {
    carRepository.deleteByNumber(number);
  }
}
