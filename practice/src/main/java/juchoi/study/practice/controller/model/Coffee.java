package juchoi.study.practice.controller.model;

import java.util.UUID;

public class Coffee {
  private String id;
  private String name;

  public Coffee(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public Coffee(String name) {
    this(UUID.randomUUID().toString(), name);
  }

  public String getName() {
    return this.name;
  }

  public String getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }
}
