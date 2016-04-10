package com.romario.mentoring.service.api;

import com.romario.mentoring.person_web_service.*;
import com.romario.mentoring.service.processor.PersonProcessor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by romario on 4/3/16.
 */
@Endpoint
public class PersonEndpoint {

  private static final String NAMESPACE_URI = "http://romario.com/mentoring/person-web-service";

  @Autowired
  private Mapper dozer;
  @Autowired
  private PersonProcessor personProcessor;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "retrievePersonRequest")
  @ResponsePayload
  public RetrievePersonResponse retrievePersonByName(
      @RequestPayload RetrievePersonRequest request) {
    RetrievePersonResponse response = new RetrievePersonResponse();
    com.romario.mentoring.model.Person person =
        personProcessor.retrievePersonByName(request.getName());
    Person result = new Person();
    result.setId(person.getId());
    result.setName(person.getName());
    result.setAge(person.getAge());
    response.setPerson(result);

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPersonRequest")
  @ResponsePayload
  public CreatePersonResponse createPerson(@RequestPayload CreatePersonRequest request) {
    CreatePersonResponse response = new CreatePersonResponse();
    com.romario.mentoring.model.Person person = new com.romario.mentoring.model.Person();
    //dozer
    dozer.map(request.getPerson(), person, "personTo");
    person = personProcessor.create(person);
    Person result = new Person();
    dozer.map(person, result, "personFrom");
    response.setPerson(result);

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
  @ResponsePayload
  public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) {
    DeletePersonResponse response = new DeletePersonResponse();
    com.romario.mentoring.model.Person person = new com.romario.mentoring.model.Person();
    dozer.map(request.getPerson(), person, "personTo");
    person = personProcessor.delete(person);
    Person result = new Person();
    dozer.map(person, result, "personFrom");
    response.setPerson(result);

    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
  @ResponsePayload
  public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
    final UpdatePersonResponse response = new UpdatePersonResponse();
    com.romario.mentoring.model.Person person = new com.romario.mentoring.model.Person();
    dozer.map(request.getPerson(), person, "personTo");
    person = personProcessor.update(person);
    Person result = new Person();
    dozer.map(person, result, "personFrom");
    return response;
  }
}
