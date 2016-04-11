package com.romario.mentoring.repository.dao;

import com.romario.mentoring.model.Person;
import com.romario.mentoring.repository.api.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Component
public class PersonDAOImpl implements PersonDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  static Person person = new Person(1, "hello", new Random().nextInt(1500));

  @Override
  public List<Person> getAllPerson() {
    List<Person> persons = new ArrayList<Person>();
    persons.add(person);
    return persons;
  }

  @Override
  public Person update(Person person) {
    String sql = "UPDATE PERSONS SET (?, ?, ?) WHERE ID = ?";

    getJdbcTemplate().update(sql, new Object[] {person.getId(), person.getName(), person.getAge(), person.getId()});
    return person;
  }

  @Override
  public Person delete(Person person) {
    String sql = "DELETE PERSONS WHERE ID = ?";
    getJdbcTemplate().update(sql, person.getId());
    return person;
  }

  @Override
  public Person create(Person person) {
    String sql = "INSERT INTO PERSONS (ID, NAME, AGE) VALUES (?, ?, ?)";

    getJdbcTemplate().update(sql, new Object[] {person.getId(), person.getName(), person.getAge()});
    return person;
  }

  @Override
  public Person findById(int id) {
    String sql = "SELECT * FROM PERSONS WHERE ID = ?";

    return getJdbcTemplate().queryForObject(sql, new Object[] {id}, new RowMapper<Person>() {
          public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"));
          }
        });
  }

  public Person findByName(String name) {
    String sql = "SELECT * FROM PERSONS WHERE \"NAME\" = ?";

    return getJdbcTemplate().queryForObject(sql, new Object[] {name}, new RowMapper<Person>() {
          public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Person(rs.getInt("ID"), rs.getString("NAME"), rs.getInt("AGE"));
          }
        });
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
}
