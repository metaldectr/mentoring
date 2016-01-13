package com.romario.mentoring.module;

/**
 * Module class
 */
public class Module {

  private int id;
  private String name;

  public Module() {
  }

  public Module(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getId());
    sb.append(" : ");
    sb.append(this.getName());
    return sb.toString();
  }
}
