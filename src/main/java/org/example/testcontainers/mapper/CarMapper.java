package org.example.testcontainers.mapper;

import org.example.testcontainers.dto.postgres.CarDto;
import org.example.testcontainers.entity.postgres.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
  Car toEntity(CarDto dto);

  CarDto toDto(Car entity);
}
