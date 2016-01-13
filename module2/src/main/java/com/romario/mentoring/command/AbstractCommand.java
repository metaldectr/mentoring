package com.romario.mentoring.command;

/**
 * Abstract command class
 */
public abstract class AbstractCommand {

  private String path;
  private String moduleName;

  public abstract void perform();

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }
}
