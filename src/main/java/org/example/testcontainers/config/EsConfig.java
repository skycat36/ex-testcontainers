package org.example.testcontainers.config;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@RequiredArgsConstructor
@EnableElasticsearchRepositories(basePackages = "org.example.testcontainers.elasticsearch")
public class EsConfig {

  @Value("${elasticsearch.uri}")
  private String EsUri;

  @Bean
  public RestHighLevelClient client() {
    ClientConfiguration clientConfiguration
            = ClientConfiguration.builder()
            .connectedTo(EsUri)
            .build();

    return RestClients.create(clientConfiguration).rest();
  }

  @Bean
  public ElasticsearchOperations elasticsearchTemplate() {
    return new ElasticsearchRestTemplate(client());
  }
}
