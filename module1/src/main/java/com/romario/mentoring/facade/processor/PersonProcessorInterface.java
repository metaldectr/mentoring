package com.romario.mentoring.facade.processor;

import com.romario.mentoring.facade.entity.Person;

import java.util.List;

/**
 * PersonProcessorInterface
 */
public interface PersonProcessorInterface
{
  String smarterPerson( final Person p1, final Person p2 );

  void moveIQForPerson( Person personFrom, Person personTo, int val );

  void changeIq( Person person, int val );

  void savePersonsInFile( List<Person> persons );

  List<Person> readPersonsFromFile();
}
