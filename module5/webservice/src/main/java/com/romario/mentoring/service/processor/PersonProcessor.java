package com.romario.mentoring.service.processor;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.repository.api.PersonDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class PersonProcessor
{
  @Resource
  private PersonDAO personDao;

  public List<Person> getAllPerson()
  {
    return personDao.getAllPerson();
  }

  public Person update( Person person )
  {
    return null;
  }

  public Person delete( Person person )
  {
    return null;
  }

  public Person create( Person person )
  {
    return personDao.create(person);
  }

  public Person retrievePersonByName(String name) {return personDao.findByName(name);}
}
