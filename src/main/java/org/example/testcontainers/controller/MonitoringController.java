package org.example.testcontainers.controller;

import org.example.testcontainers.dto.couchbase.DailyProgressDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

public interface MonitoringController {

  @PostMapping(value = "/api/progress")
  DailyProgressDto addDailyProgress(@RequestBody DailyProgressDto dailyProgressDto);

  @GetMapping(value = "/api/progress/{carNumber}")
  List<DailyProgressDto> getDailyProgressCar(@PathVariable Long carNumber);
}
