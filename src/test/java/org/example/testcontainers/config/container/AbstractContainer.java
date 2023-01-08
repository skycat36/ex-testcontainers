package org.example.testcontainers.config.container;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.GenericContainer;

@Slf4j
public abstract class AbstractContainer implements ContainerStarter {

  @SuppressWarnings("all")
  public final GenericContainer CONTAINER;

  protected <CONTAINER extends GenericContainer<CONTAINER>> AbstractContainer(CONTAINER container) {
    this.CONTAINER = container;
  }

  @Override
  @SneakyThrows
  public void initContainer() {
    try {
      if (!CONTAINER.isRunning()) {
        CONTAINER.start();
      }
    } catch (Exception ex) {
      log.error("Container incorrect start, release a server resource. {}", ex.getMessage());
      CONTAINER.stop();
    }
  }

  @Override
  public void stopContainer() {
    if (CONTAINER.isRunning()) {
      CONTAINER.stop();
    }
  }

  @Override
  public boolean isContainerStart() {
    try {
      return CONTAINER.isRunning();
    } catch (Exception ex) {
      log.error("Container not started. {}", ex.getMessage());
      return false;
    }
  }
}
