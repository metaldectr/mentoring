package com.romario.mentoring.facade.processor;

import com.romario.mentoring.facade.entity.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Person Processor
 * <p/>
 * Facade
 */
public class PersonProcessor implements PersonProcessorInterface
{
  final Logger logger = Logger.getLogger( PersonProcessor.class.getName() );
  private final static String URL = "/persons";

  private Person person;

  public PersonProcessor( String name, int age, int iq )
  {
    this.person = new Person( name, age, iq );
  }

  /**
   * @param p1
   * @param p2
   * @return
   */
  public String smarterPerson( final Person p1, final Person p2 )
  {
    if ( p1 != null && p2 != null ) {
      if ( p1.getIq() > p2.getIq() ) {
        return p1.getName();
      } else if ( p2.getIq() > p1.getIq() ) {
        return p2.getName();
      } else {
        return "Similar person by IQ";
      }
    }
    return null;
  }

  /**
   *
   * @param personFrom
   * @param personTo
   * @param val
   */
  public void moveIQForPerson( Person personFrom, Person personTo, int val )
  {
    personFrom.setIq( personFrom.getIq() - val );
    personTo.setIq( personTo.getIq() + val );
  }

  public void changeIq( Person person, int val )
  {
    person.setIq( person.getIq() + val );
  }

  /**
   * @param persons
   */
  public void savePersonsInFile( List<Person> persons )
  {
    try (ObjectOutputStream out = new ObjectOutputStream(
      new FileOutputStream( URL ) )) {
      out.writeObject( persons );
    } catch( final IOException e ) {
      logger.log( Level.FINE, "cannot create/open/change file" );
    }
  }

  /**
   * @return
   */
  public List<Person> readPersonsFromFile()
  {
    try (ObjectInputStream in = new ObjectInputStream(
      new FileInputStream( URL ) )) {

      return (List<Person>)in.readObject();
    } catch( final IOException e ) {
      logger.log( Level.FINE, "cannot /open/read/change file" );
    } catch( final ClassNotFoundException cce ) {
      logger.log( Level.FINE, "cannot found class" );
    }
    return null;
  }

  public Person getPerson()
  {
    return person;
  }

  public void setPerson( Person person )
  {
    this.person = person;
  }
}
