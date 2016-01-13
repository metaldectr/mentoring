package com.romario.mentoring.module;

/**
 * Module class
 */
public class MyModule implements Module {

  private int id = 0;
  private String name;

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
