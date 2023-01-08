package org.example.testcontainers.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.entity.elasticsearch.DailyProgress;
import org.example.testcontainers.entity.postgres.Car;
import org.example.testcontainers.elasticsearch.DailyProgressRepository;
import org.example.testcontainers.repository.postgres.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class GlobalTestRepository {
  private Map<Class<?>, CrudRepository<?, ?>> entityClassRepositoryMap;

  @Autowired
  private CarRepository carRepository;
  @Autowired
  private DailyProgressRepository dailyProgressRepository;

  public static final Long DURATION_WAIT_AFTER_SAVE = Duration.ofMillis(500).toMillis();

  @PostConstruct
  private void init() {
    this.entityClassRepositoryMap = new LinkedHashMap<>();
    entityClassRepositoryMap.put(Car.class, carRepository);

    entityClassRepositoryMap.put(DailyProgress.class, dailyProgressRepository);
  }

  /**
   * Save entity.
   *
   * @param entities entity to save.
   */
  @SneakyThrows
  @SuppressWarnings("all")
  public void saveEntity(Object... entities) {
    for (Object entity: entities) {
      CrudRepository repo = entityClassRepositoryMap.get(entity.getClass());
      if (repo == null) {
        throw new IllegalArgumentException("Repository for object not exist");
      }
      repo.save(entity);
    }
    Thread.sleep(DURATION_WAIT_AFTER_SAVE);
  }

  /**
   * Delete all.
   */
  @SneakyThrows
  public void deleteAll() {
    entityClassRepositoryMap.values().forEach(CrudRepository::deleteAll);
    Thread.sleep(DURATION_WAIT_AFTER_SAVE);
  }
}
