package com.romario.mentoring.webservice.api;

import com.romario.mentoring.webservice.model.Person;
import com.romario.mentoring.webservice.processor.PersonProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by romario
 */
@RestController
@RequestMapping(value = "/personService")
public class PersonService {

  private static final Logger LOGGER = Logger.getLogger(
    PersonService.class.getName() );
  @Autowired
  private PersonProcessor personProcessor;

  @RequestMapping(method = RequestMethod.GET, value = "/{departmentId}")
  public List<Person> getAllPersonsFromDepartment(@PathVariable("departmentId") Integer departmentId) {
    return personProcessor.getPersonsFromDepartment( departmentId );
  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public Person createPerson( @RequestBody Person person )
  {
    LOGGER.info( "Start operation createPerson" );
    return personProcessor.createPerson( person );
  }

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public List<Person> readPersons() {
    LOGGER.info("Start operation readPersons");
    return personProcessor.getPersons();
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/", consumes = MediaType.APPLICATION_JSON)
  public Person updatePerson( @RequestBody Person person )
  {
    LOGGER.info( "Start operation updatePerson" );
    return personProcessor.updatePerson( person );
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/", consumes = MediaType.APPLICATION_JSON)
  public Person removePerson( @RequestBody Person person )
  {
    LOGGER.info( "Start operation removePerson" );
    return personProcessor.removePerson( person );
  }

}
