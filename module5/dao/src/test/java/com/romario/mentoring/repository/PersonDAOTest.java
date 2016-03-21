package com.romario.mentoring.repository;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.repository.dao.PersonDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonDAOTest
{
  EmbeddedDatabase db;

  @Before
  public void setUp()
  {
    db = new EmbeddedDatabaseBuilder()
      .setType( EmbeddedDatabaseType.H2 )
      .addScript( "createDB.sql" )
      .addScript( "insertDB.sql" )
      .build();
  }

  @Test
  public void getPersonByIdTest()
  {
    JdbcTemplate template = new JdbcTemplate( db );
    PersonDAOImpl personDAO = new PersonDAOImpl();
    personDAO.setJdbcTemplate( template );

    Person person = personDAO.findById( 1 );

    assertNotNull( person );
    assertEquals( 1, person.getId() );
    assertEquals( "person1", person.getName() );
    assertEquals( 22, person.getAge() );
  }

  @After
  public void tearDown()
  {
    db.shutdown();
  }

}
