package com.romario.mentoring.facade.entity;

import java.io.Serializable;

/**
 * Model a person
 */
public class Person
  implements Serializable
{

  private String name;
  private int age;
  private int iq;

  public Person()
  {
  }

  /**
   * @param name
   * @param age
   * @param iq
   */
  public Person( String name, int age, int iq )
  {
    this.name = name;
    this.age = age;
    this.iq = iq;
  }

  public int getIq()
  {
    return iq;
  }

  public void setIq( int iq )
  {
    this.iq = iq;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge( int age )
  {
    this.age = age;
  }
}
