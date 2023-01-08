package org.example.testcontainers.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.dto.couchbase.DailyProgressDto;
import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import org.example.testcontainers.facade.DailyProgressFacade;
import org.example.testcontainers.mapper.DailyProgressMapper;
import org.example.testcontainers.service.DailyProgressService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class DailyProgressFacadeImpl implements DailyProgressFacade {

  private final DailyProgressService dailyProgressService;
  private final DailyProgressMapper dailyProgressMapper;

  @Override
  public DailyProgressDto save(DailyProgressDto dailyProgressDto) {
    return dailyProgressMapper.toDto(dailyProgressService.save(dailyProgressMapper.toEntity(dailyProgressDto)));
  }

  @Override
  public List<DailyProgressDto> getByCarNumber(Long carNumber) {
    List<DailyProgress> dailyProgressList = dailyProgressService.getByCarNumber(carNumber);
    return dailyProgressList.stream().map(dailyProgressMapper::toDto).collect(Collectors.toList());
  }
}
