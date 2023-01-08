package org.example.testcontainers.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import org.example.testcontainers.elasticsearch.DailyProgressRepository;
import org.example.testcontainers.service.DailyProgressService;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class DailyProgressServiceImpl implements DailyProgressService {

  private final DailyProgressRepository dailyProgressRepository;

  @Override
  public DailyProgress save(DailyProgress car) {
    return dailyProgressRepository.save(car);
  }

  @Override
  public List<DailyProgress> getByCarNumber(Long carNumber) {
    return dailyProgressRepository.findAllByCarNumber(carNumber);
  }
}
