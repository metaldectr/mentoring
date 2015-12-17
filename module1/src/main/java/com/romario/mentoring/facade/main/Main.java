package com.romario.mentoring.facade.main;

import com.romario.mentoring.facade.entity.Person;
import com.romario.mentoring.facade.processor.PersonProcessor;

/**
 * Main class
 */
public class Main
{
  public static void main( String[] args )
  {
    Person p1 = new Person( "person1", 23, 100 );
    Person p2 = new Person( "person2", 21, 101 );

    PersonProcessor processor = new PersonProcessor();
  }

}
