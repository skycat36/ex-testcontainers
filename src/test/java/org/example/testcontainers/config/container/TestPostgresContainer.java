package org.example.testcontainers.config.container;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@Slf4j
@Component
public class TestPostgresContainer extends AbstractContainer {

  private static final String IMAGE_VERSION = "postgres:13.3";

  @Container
  @SuppressWarnings("all")
  public static final PostgreSQLContainer TEST_POSTGRES_CONTAINER = new PostgreSQLContainer(IMAGE_VERSION)
          .withUsername("postgres")
          .withPassword("postgres")
          .withDatabaseName("extest");

  @SuppressWarnings("all")
  protected TestPostgresContainer() {
    super(TEST_POSTGRES_CONTAINER);
  }
}