package org.example.testcontainers.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.controller.MonitoringController;
import org.example.testcontainers.dto.couchbase.DailyProgressDto;
import org.example.testcontainers.facade.DailyProgressFacade;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MonitoringControllerImpl implements MonitoringController {

  private final DailyProgressFacade dailyProgressFacade;

  @Override
  public DailyProgressDto addDailyProgress(DailyProgressDto dailyProgressDto) {
    return dailyProgressFacade.save(dailyProgressDto);
  }

  @Override
  public List<DailyProgressDto> getDailyProgressCar(Long carNumber) {
    return dailyProgressFacade.getByCarNumber(carNumber);
  }
}
