package com.romario.mentoring.command;

import com.romario.mentoring.classloader.MyClassLoader;

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
    final String path = answer("Enter the path to class in module:");
    final String moduleName = answer("Enter the module name:");
    final MyClassLoader myClassLoader = new MyClassLoader();
    try {
      myClassLoader.loadClass(path, moduleName);
    } catch (ClassNotFoundException e) {
      sLogger.error("Couldn't find class by path : " + path, e);
    }
  }

  private String answer(String message) {
    System.out.println(message);
    return scanner.next();
  }

}
