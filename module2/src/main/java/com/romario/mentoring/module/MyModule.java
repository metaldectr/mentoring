package com.romario.mentoring.module;

/**
 * Module class
 */
public class MyModule implements Module {

  private int id = 0;
  private String name;

  public MyModule() {
    id++;
  }

  /* (non-Javadoc)
   * @see com.romario.mentoring.module.Module#getId()
   */
  public int getId() {
    return id;
  }

  /* (non-Javadoc)
   * @see com.romario.mentoring.module.Module#getName()
   */
  public String getName() {
    return name;
  }

  /* (non-Javadoc)
   * @see com.romario.mentoring.module.Module#setName(java.lang.String)
   */
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
