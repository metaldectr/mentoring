package com.romario.mentoring.command;

import com.romario.mentoring.classloader.MyClassLoader;
import org.apache.log4j.Logger;

/**
 * MyClassloader command class
 */
public class ClassLoaderCommand extends AbstractCommand {

  private final Logger sLogger = Logger.getLogger(ClassLoaderCommand.class);

  @Override
  public void perform() {
    final MyClassLoader myClassLoader = new MyClassLoader();
    try {
      myClassLoader.loadClass(getPath(), getModuleName());
    } catch (ClassNotFoundException e) {
      sLogger.error("Couldn't find class by path : " + getPath(), e);
    }
  }

}
