package org.example.testcontainers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.testcontainers.ExampleApplication;
import org.example.testcontainers.config.ElasticsearchTestConfig;
import org.example.testcontainers.config.GlobalTestRepository;
import org.example.testcontainers.config.PostgresDbTestConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = ExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@Testcontainers(disabledWithoutDocker = true)
@ActiveProfiles("test")
@ContextConfiguration(classes = {ElasticsearchTestConfig.class, PostgresDbTestConfiguration.class})
public abstract class AbstractControllerTest {
@Autowired
public MockMvc mockMvc;
@Autowired
public GlobalTestRepository globalTestRepository;
@Autowired
public ObjectMapper objectMapper;

  @AfterEach
  void tearDown() {
    globalTestRepository.deleteAll();
  }
}
