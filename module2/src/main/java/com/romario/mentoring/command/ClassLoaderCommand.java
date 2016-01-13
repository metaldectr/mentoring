package com.romario.mentoring.command;

import com.romario.mentoring.classloader.MyClassLoader;
import com.romario.mentoring.container.Container;
import com.romario.mentoring.module.Module;

import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * MyClassloader command class
 */
public class ClassLoaderCommand implements Command {
	
  private final Logger sLogger = Logger.getLogger(ClassLoaderCommand.class);

  private Scanner scanner;

  public ClassLoaderCommand(final Scanner scanner) {
    this.scanner = scanner;
  }

  public void perform() {
    final String className = answer("Enter class name:");
    final String moduleName = answer("Enter the module name:");
    final MyClassLoader classLoader = new MyClassLoader();
    try {
      Module module = loadModule(className, moduleName, classLoader);
      Container.getInstance().getClassesMap().put(module.getId(), module);
    } catch (Exception e) {
      sLogger.error("Couldn't load class by path : " + className, e);
    }
  }

  @SuppressWarnings("unchecked")
  private Module loadModule(final String className, final String moduleName, final MyClassLoader myClassLoader)
      throws Exception {
    Class<Module> clazz = (Class<Module>) myClassLoader.loadClass(className);
    Module module = clazz.newInstance();
    module.setName(moduleName);
    return module;
  }

  private String answer(String message) {
    System.out.println(message);
    return scanner.next();
  }

}
