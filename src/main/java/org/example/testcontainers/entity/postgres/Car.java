package org.example.testcontainers.entity.postgres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "car", schema = "extest")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extest.car_sequence")
  @SequenceGenerator(name = "extest.car_sequence", sequenceName = "extest.car_sequence", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "brand", nullable = false)
  private String brand;

  @Column(name = "number", nullable = false)
  private Long number;

  @Column(name = "mileage")
  private Integer mileage;

  @Column(name = "last_service_time")
  private LocalDate lastServiceTime;
}
