package org.example.testcontainers.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.example.testcontainers.config.container.IntegrationTestConfig;
import org.example.testcontainers.config.container.TestPostgresContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Slf4j
@TestConfiguration("PostgresDbConfiguration")
@EnableConfigurationProperties(value = {DataSourceProperties.class})
public class PostgresDbTestConfiguration {
  private final DataSourceProperties dataSourceProperties;
  @Autowired IntegrationTestConfig integrationTestConfig;

  /**
   * Constructor test config.
   * */
  public PostgresDbTestConfiguration(@Autowired IntegrationTestConfig integrationTestConfig,
                                     DataSourceProperties dataSourceProperties) {
    this.dataSourceProperties = dataSourceProperties;
    integrationTestConfig.initContainers();
  }

  /**
   * Configure connection to DB.
   */
  @Primary
  @Bean(name = "dataSource")
  public DataSource dataSource() {
    final HikariDataSource dataSource = new HikariDataSource();
    final HikariConfig config = new HikariConfig();

    config.setPassword(dataSourceProperties.getPassword());
    config.setUsername(dataSourceProperties.getUsername());
    config.setDriverClassName(dataSourceProperties.getDriverClassName());
    config.setJdbcUrl(TestPostgresContainer.TEST_POSTGRES_CONTAINER.getJdbcUrl());
    dataSource.setMaximumPoolSize(50);
    config.setConnectionTestQuery("SELECT 1");
    return new HikariDataSource(config);
  }
}
