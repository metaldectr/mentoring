package com.romario.mentoring.service.api

import com.romario.mentoring.service.processor.PersonProcessor
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by romario on 3/3/16.
 */
class PersonControllerTest1 extends Specification {

    def personController = new PersonService()
    def personProcessor = Mock(PersonProcessor)

    def magic = MockMvcBuilders.standaloneSetup(personController).build()

    @Unroll
    def "test get allPersons" () {
        when:
            def response = magic.perform(get('/personService')).andReturn().response

        then:
            1 * personProcessor.getAllPerson() >> '[{"id":1,"name":"hello","age":24}]'

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath('$.name', is('hello')))
    }
}
