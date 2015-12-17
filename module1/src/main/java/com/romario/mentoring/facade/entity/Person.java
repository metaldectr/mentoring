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
}
