package org.example.testcontainers.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.dto.postgres.CarDto;
import org.example.testcontainers.facade.CarFacade;
import org.example.testcontainers.mapper.CarMapper;
import org.example.testcontainers.service.CarService;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarFacadeImpl implements CarFacade {

  private final CarService carService;
  private final CarMapper carMapper;

  @Override
  public CarDto save(CarDto carDto) {
    return carMapper.toDto(carService.save(carMapper.toEntity(carDto)));
  }

  @Override
  public CarDto getByNumber(Long number) {
    return carMapper.toDto(carService.getByNumber(number));
  }

  @Override
  public void deleteByNumber(Long number) {
    carService.deleteByNumber(number);
  }
}
