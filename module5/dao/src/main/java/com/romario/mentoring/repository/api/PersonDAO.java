package com.romario.mentoring.repository.api;

import com.romario.mentoring.model.Person;

import java.util.List;

/**
 * Created by Raman_Zhuravski on 2/26/2016.
 */
public interface PersonDAO
{

  List<Person> getAllPerson();

  Person update( Person person );

  Person delete( Person person );

  Person create( Person person );


}
