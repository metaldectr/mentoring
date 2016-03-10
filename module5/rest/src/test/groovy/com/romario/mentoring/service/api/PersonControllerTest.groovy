package com.romario.mentoring.service.api

import com.romario.mentoring.model.Person
import com.romario.mentoring.service.processor.PersonProcessor
import groovy.json.JsonSlurper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import spock.lang.Specification

/**
 * Created by romario on 3/3/16.
 */
class PersonControllerTest extends Specification {

    def res = "[{\"id\":1,\"name\":\"hello\",\"age\":24}]"
    List<Person> persons = new ArrayList<Person>()
    def personController = new PersonService()
    def personProcessor = Mock(PersonProcessor)
    MockMvc magic

    def setup() {
        magic = MockMvcBuilders.standaloneSetup(personController).build()
        personController.personProcessor = personProcessor
    }
    def "test get allPersons" () {
        given:
            String res = "[{\"id\":1,\"name\":\"hello\",\"age\":24}]"
            List<Person> persons = new ArrayList<Person>()
        when:
            def response = magic.perform(get('/personService')).andReturn().response

        then:
           1 * personProcessor.getAllPerson() >> persons
/*
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath('$.name', is('hello')))*/
    }
}
