package org.example.testcontainers.config.container;

public interface ContainerStarter {
  void initContainer();

  void stopContainer();

  boolean isContainerStart();
}
