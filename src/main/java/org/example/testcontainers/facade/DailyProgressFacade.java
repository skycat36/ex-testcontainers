package org.example.testcontainers.facade;

import org.example.testcontainers.dto.couchbase.DailyProgressDto;

import java.util.List;

public interface DailyProgressFacade {

  DailyProgressDto save(DailyProgressDto dailyProgressDto);

  List<DailyProgressDto> getByCarNumber(Long carNumber);

}
