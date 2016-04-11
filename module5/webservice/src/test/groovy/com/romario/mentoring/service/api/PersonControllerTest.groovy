package com.romario.mentoring.service.api

import com.romario.mentoring.model.Person
import com.romario.mentoring.service.processor.PersonProcessor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.is
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Created by romario on 3/3/16.
 */
class PersonControllerTest extends Specification {

    def processor = Mock(PersonProcessor)
    def personController = new PersonService(personProcessor: processor)
    MockMvc magic = MockMvcBuilders.standaloneSetup(personController).build()

    def "test get allPersons"() {
        when:
            def response = magic.perform(get('/personService'))

        then:
            1 * processor.getAllPerson() >> [new Person(id: 1, name: "hello", age: 24)]
            response
                .andExpect(status().isOk())
                .andExpect(jsonPath('$[0].name', is('hello')))
    }
}
