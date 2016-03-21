package com.romario.mentoring.repository.api;

import com.romario.mentoring.model.Person;

import java.util.List;

public interface PersonDAO
{

  List<Person> getAllPerson();

  Person update( Person person );

  Person delete( Person person );

  Person create( Person person );

  Person findById( int id );

}
