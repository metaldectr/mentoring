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
public class PersonProcessor
{
  final Logger logger = Logger.getLogger( PersonProcessor.class.getName() );
  private final static String URL = "/persons";

  public Person smarterPerson( final Person p1, final Person p2 )
  {
    if ( p1 != null && p2 != null ) {
      if ( p1.getIq() > p2.getIq() ) {
        return p1;
      } else {
        return p2;
      }
    }
    return null;
  }

  public void moveIQForPerson( Person personFrom, Person personTo, int val )
  {
    personFrom.setIq( personFrom.getIq() - val );
    personTo.setIq( personTo.getIq() + val );
  }

  public void changeIq( Person person, int val )
  {
    person.setIq( person.getIq() + val );
  }

  public void savePersonsInFile( List<Person> persons )
  {
    try (ObjectOutputStream out = new ObjectOutputStream(
      new FileOutputStream( URL ) )) {
      out.writeObject( persons );
    } catch( final IOException e ) {
      logger.log( Level.FINE, "cannot create/open/change file" );
    }
  }

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

}
