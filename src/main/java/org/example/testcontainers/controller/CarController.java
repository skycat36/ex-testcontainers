package org.example.testcontainers.controller;

import org.example.testcontainers.dto.postgres.CarDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CarController {

  @PostMapping(value = "/api/car")
  CarDto save(@RequestBody CarDto carDto);

  @GetMapping(value = "/api/car/{number}")
  CarDto getCarByNumber(@PathVariable Long number);

  @DeleteMapping(value = "/api/car/{number}")
  void deleteCarByNumber(@PathVariable Long number);
}
