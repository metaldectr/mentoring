package com.romario.mentoring.repository.dao;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.repository.api.PersonDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raman_Zhuravski on 3/1/2016.
 */
@Component
public class PersonDAOImpl implements PersonDAO
{
  @Override
  public List<Person> getAllPerson()
  {
    List<Person> persons = new ArrayList<Person>(  );
    persons.add( new Person( 1, "hello", 24 ) );
    return persons;
  }

  @Override
  public Person update( Person person )
  {
    return null;
  }

  @Override
  public Person delete( Person person )
  {
    return null;
  }

  @Override
  public Person create( Person person )
  {
    return null;
  }
}
