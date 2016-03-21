package com.romario.mentoring.repository.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@ComponentScan({ "com.romario.mentoring" })
@Configuration
public class SpringConfiguration
{

  @Bean
  public JdbcTemplate getJdbcTemplate()
  {
    return new JdbcTemplate( dataSource() );
  }

  @Bean
  public DataSource dataSource()
  {

    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db = builder
      .setType( EmbeddedDatabaseType.H2 )
      .addScript( "createDB.sql" )
      .addScript( "insertDB.sql" )
      .build();
    return db;
  }
}
