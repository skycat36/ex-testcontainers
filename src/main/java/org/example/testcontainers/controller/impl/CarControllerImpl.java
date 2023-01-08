package org.example.testcontainers.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.controller.CarController;
import org.example.testcontainers.dto.postgres.CarDto;
import org.example.testcontainers.facade.CarFacade;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CarControllerImpl implements CarController {

  private final CarFacade carFacade;

  @Override
  public CarDto save(CarDto carDto) {
    return carFacade.save(carDto);
  }

  @Override
  public CarDto getCarByNumber(Long number) {
    return carFacade.getByNumber(number);
  }

  @Override
  public void deleteCarByNumber(Long number) {
    carFacade.deleteByNumber(number);
  }
}
