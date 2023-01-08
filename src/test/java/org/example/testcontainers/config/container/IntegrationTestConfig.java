package org.example.testcontainers.config.container;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class IntegrationTestConfig {

//  private final TestCouchbaseContainer testCouchbaseContainer;

  private final List<ContainerStarter> containerStarterList;

  @PostConstruct
  void init() {
    initContainers();
  }

  /**
   * Init Docker CouchBase container for testing.
   */
  @SuppressWarnings("All")
  @SneakyThrows
  public void initContainers() {
    while (!isContainersStart()) {
      try {
        containerStarterList.forEach(ContainerStarter::initContainer);
        Thread.sleep(10);
      } catch (Exception ex) {
        log.error("Container incorrect start, release a server resource. {}", ex.getMessage());
        containerStarterList.forEach(ContainerStarter::stopContainer);
      }
    }
  }

  /**
   * Check is start containers.
   */
  public boolean isContainersStart() {
    try {
      return containerStarterList.stream().allMatch(ContainerStarter::isContainerStart);
    } catch (Exception ex) {
      log.error("Container not started. {}", ex.getMessage());
      return false;
    }
  }
}
