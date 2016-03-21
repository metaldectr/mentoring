package com.romario.mentoring.repository.dao;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.repository.api.PersonDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAOImpl
  implements PersonDAO
{
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Person> getAllPerson()
  {
    List<Person> persons = new ArrayList<Person>();
    persons.add( new Person( 1, "hello", 24 ) );
    return persons;
  }

  @Override
  public Person update( Person person )
  {
    return null;
  }

  @Override
  public Person delete( Person person )
  {
    return null;
  }

  @Override
  public Person create( Person person )
  {
    return null;
  }

  @Override
  public Person findById( int id )
  {
    String sql = "SELECT * FROM PERSONS WHERE ID = ?";

    return getJdbcTemplate().queryForObject(
      sql, new Object[] { id }, new RowMapper<Person>()
      {
        public Person mapRow( ResultSet rs, int rowNum )
          throws SQLException
        {
          return new Person(
            rs.getInt( "ID" ), rs.getString( "NAME" ), rs.getInt( "AGE" ) );
        }
      } );
  }

  public JdbcTemplate getJdbcTemplate()
  {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(
    JdbcTemplate jdbcTemplate )
  {
    this.jdbcTemplate = jdbcTemplate;
  }
}
