package org.example.testcontainers.dto.postgres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
  private Long id;

  private String brand;

  private Long number;

  private Integer mileage;

  private LocalDate lastServiceTime;
}
