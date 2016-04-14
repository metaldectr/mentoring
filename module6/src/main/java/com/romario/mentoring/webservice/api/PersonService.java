package com.romario.mentoring.webservice.api;

import com.romario.mentoring.webservice.model.Person;
import com.romario.mentoring.webservice.processor.PersonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by romario
 */
@RestController
@RequestMapping(value = "/personService")
public class PersonService {

  @Autowired
  private PersonProcessor personProcessor;

  @RequestMapping(method = RequestMethod.GET, value = "/{departmentId}")
  public List<Person> getAllPersonsFromDepartment(@PathVariable("departmentId") Integer departmentId) {
    return personProcessor.getPersonsFromDepartment( departmentId );
  }

}
