package com.romario.mentoring.service.api;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.service.processor.PersonProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Raman_Zhuravski on 2/24/2016.
 */
@Controller
@RequestMapping(value = "/personService")
public class PersonService
{
  @Resource
  private PersonProcessor personProcessor;

  @RequestMapping(method = RequestMethod.GET )
  @ResponseBody
  public List<Person> getAllPerson()
  {
    return personProcessor.getAllPerson();
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
