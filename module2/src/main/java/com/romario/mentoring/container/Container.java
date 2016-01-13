package com.romario.mentoring.container;

import java.util.HashMap;
import java.util.Map;

/**
 * Container
 */
public class Container {

  private Map<Integer, Object> classesMap = new HashMap<Integer, Object>();
  private static final Container INSTANCE = new Container();

  private Container() {}

  public static Container getInstance() {
    return INSTANCE;
  }

  public Map<Integer, Object> getClassesMap() {
    return classesMap;
  }

  public void setClassesMap(Map<Integer, Object> classesMap) {
    this.classesMap = classesMap;
  }
}
