package org.example.testcontainers.controller;

import org.example.testcontainers.dto.couchbase.DailyProgressDto;
import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import org.example.testcontainers.entity.postgres.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Check work controller MonitoringController")
public class MonitoringControllerTest extends AbstractControllerTest {

  @Test
  @DisplayName("GET | Get car by number")
  void addDailyProgress() throws Exception {
    Car car = Car.builder().brand("KamaZ").number(12345L).mileage(10000).build();
    DailyProgress dailyProgress = DailyProgress.builder()
            .date(LocalDate.now())
            .carNumber(12345L)
            .miles(100)
            .build();

    globalTestRepository.saveEntity(car, dailyProgress);

    DailyProgressDto payload = DailyProgressDto.builder()
            .date(LocalDate.now())
            .carNumber(12345L)
            .miles(100)
            .build();


    mockMvc.perform(post("/api/progress")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(payload)))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("GET | Get car by number")
  void getDailyProgressCar() throws Exception {
    Long carNumber = 12345L;
    Car car = Car.builder().brand("KamaZ").number(carNumber).mileage(10000).build();

    globalTestRepository.saveEntity(car,
            DailyProgress.builder()
                    .date(LocalDate.now())
                    .carNumber(carNumber)
                    .miles(100)
                    .build(),
            DailyProgress.builder()
                    .date(LocalDate.now())
                    .carNumber(carNumber)
                    .miles(200)
                    .build());

    mockMvc.perform(get("/api/progress/{carNumber}", carNumber)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}
