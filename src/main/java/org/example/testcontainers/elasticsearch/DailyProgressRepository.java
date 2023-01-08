package org.example.testcontainers.elasticsearch;

import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DailyProgressRepository extends ElasticsearchRepository<DailyProgress, String> {

  List<DailyProgress> findAllByCarNumber(Long carNumber);
}
