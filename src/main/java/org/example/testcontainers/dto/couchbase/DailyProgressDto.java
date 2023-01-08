package org.example.testcontainers.dto.couchbase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyProgressDto {

  private Long carNumber;

  private Integer miles;

  private LocalDate date;
}
