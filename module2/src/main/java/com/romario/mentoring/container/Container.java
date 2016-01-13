package com.romario.mentoring.container;

import java.util.HashMap;
import java.util.Map;

/**
 * Container
 */
public class Container {

  private Map<Integer, Object> classesMap;
  private static volatile Container container;

  private Container() {
    classesMap = new HashMap<Integer, Object>();
  }

  public static Container getInstance() {
    Container tmpContainer = container;
    if (tmpContainer == null) {
      synchronized (Container.class) {
        tmpContainer = container;
        if (container == null) {
          tmpContainer = container = new Container();
        }
      }
    }
    return tmpContainer;
  }

  public Map<Integer, Object> getClassesMap() {
    return classesMap;
  }

  public void setClassesMap(Map<Integer, Object> classesMap) {
    this.classesMap = classesMap;
  }
}
