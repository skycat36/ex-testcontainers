package org.example.testcontainers.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.example.testcontainers.config.container.IntegrationTestConfig;
import org.example.testcontainers.config.container.TestElasticsearchContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

@Slf4j
@TestConfiguration("EsConfig")
public class ElasticsearchTestConfig extends EsConfig {

  /**
   * Constructor ElasticsearchClientTestConfig.
   * */
  public ElasticsearchTestConfig(
          @Autowired IntegrationTestConfig integrationTestConfig) {
    super();
    integrationTestConfig.initContainers();
  }

  @Override
  public RestHighLevelClient client() {
    return RestClients.create(clientConfiguration()).rest();
  }

  /**
   * Build clientConfiguration.
   *
   * @return clientConfiguration based on active profile
   */
  private ClientConfiguration clientConfiguration() {
    return ClientConfiguration
            .builder()
            .connectedTo(TestElasticsearchContainer.ELASTICSEARCH_CONTAINER.getHttpHostAddress())
            .withSocketTimeout(1500000)
            .build();
  }
}