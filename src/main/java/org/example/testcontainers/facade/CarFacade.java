package org.example.testcontainers.facade;

import org.example.testcontainers.dto.postgres.CarDto;

public interface CarFacade {

  CarDto save(CarDto carDto);

  CarDto getByNumber(Long number);

  void deleteByNumber(Long number);
}
