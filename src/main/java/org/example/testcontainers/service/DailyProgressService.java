package org.example.testcontainers.service;

import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import java.util.List;

public interface DailyProgressService {

  DailyProgress save(DailyProgress car);

  List<DailyProgress> getByCarNumber(Long carNumber);
}
