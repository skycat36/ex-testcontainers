package org.example.testcontainers.controller;

import org.example.testcontainers.dto.postgres.CarDto;
import org.example.testcontainers.entity.postgres.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Check work controller CarController")
public class CarControllerTest extends AbstractControllerTest {

  @Test
  @DisplayName("POST | Save car")
  public void save() throws Exception {
    CarDto carDto = CarDto.builder().brand("KamaZ").number(12345L).build();

    mockMvc.perform(post("/api/car")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(carDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.brand").value(carDto.getBrand()));
  }

  @Test
  @DisplayName("GET | Get car by number")
  public void getCarByNumber() throws Exception {
    Long numberCar = 12345L;
    Car car = Car.builder().brand("KamaZ").number(numberCar).mileage(10000).build();

    globalTestRepository.saveEntity(car);

    mockMvc.perform(get("/api/car/{number}", numberCar))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.brand").value(car.getBrand()))
            .andExpect(jsonPath("$.number").value(car.getNumber()));
  }

  @Test
  @DisplayName("DELETE | Delete car by number")
  public void deleteCarByNumber() throws Exception {
    Long numberCar = 12345L;
    Car car = Car.builder().brand("KamaZ").number(numberCar).mileage(10000).build();

    globalTestRepository.saveEntity(car);

    mockMvc.perform(delete("/api/car/{number}", numberCar))
            .andExpect(status().isOk());
  }
}
