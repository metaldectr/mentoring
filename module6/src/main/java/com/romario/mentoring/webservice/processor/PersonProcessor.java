package com.romario.mentoring.webservice.processor;

import com.romario.mentoring.webservice.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romario
 */
@Component
public class PersonProcessor {

  public List<Person> getPersonsFromDepartment(int departmentId) {
    return new ArrayList<Person>();
  }
}
