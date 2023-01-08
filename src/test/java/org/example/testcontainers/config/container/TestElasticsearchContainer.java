package org.example.testcontainers.config.container;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;

/**
 *  Test elasticsearch container.
 */
@Slf4j
@Component
public class TestElasticsearchContainer extends AbstractContainer {

  private static final String ELASTIC_SEARCH_DOCKER = "docker.elastic.co/elasticsearch/elasticsearch:7.14.0";

  @Container
  public static final ElasticsearchContainer ELASTICSEARCH_CONTAINER = new ElasticsearchContainer(ELASTIC_SEARCH_DOCKER);

  protected TestElasticsearchContainer() {
    super(ELASTICSEARCH_CONTAINER);
  }
}
