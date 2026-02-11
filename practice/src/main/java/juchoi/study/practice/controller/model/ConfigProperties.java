package juchoi.study.practice.controller.model;

import java.util.UUID;

public class ConfigProperties {
  private String id, description;

  public ConfigProperties() {
  }

  public ConfigProperties(String id, String desc) {
    this.id = id;
    this.description = desc;
  }

  public ConfigProperties(String desc) {
    this(UUID.randomUUID().toString(), desc);
  }

  public String getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setDescription(String desc) {
    this.description = desc;
  }
}
