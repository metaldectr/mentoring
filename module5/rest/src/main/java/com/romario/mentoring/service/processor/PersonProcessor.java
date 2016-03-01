package com.romario.mentoring.service.processor;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.repository.api.PersonDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raman_Zhuravski on 2/24/2016.
 */
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
    return null;
  }
}
