package com.romario.mentoring.container;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.romario.mentoring.module.Module;

/**
 * Container
 */
public class Container {

  private Map<Integer, Module> modules = new HashMap<Integer, Module>();
  private static final Container INSTANCE = new Container();

  private Container() {}

  public static Container getInstance() {
    return INSTANCE;
  }

  public Module get(Integer key) {
    return modules.get(key);
  }

  public Module put(Integer key, Module module) {
    return modules.put(key, module);
  }
  
  public Collection<Module> getAll(){
    return modules.values();
  }
}
