package com.romario.mentoring.observer.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MyFileReader
 */
public class MyFileReader
  extends Observable
{
  private final static Logger LOGGER = Logger.getLogger( MyFileReader.class.getName() );

  final List<Observer> observers = new ArrayList<>();

  public void readFile( String path )
  {
    try {
      Scanner scanner = new Scanner( new File( path ) );
      while ( scanner.hasNextLine() ) {
        Scanner scanner2 = new Scanner( scanner.nextLine() );
        while ( scanner2.hasNext() ) {
          String s = scanner2.next();
          notifyObservers( s );
        }
      }
    } catch( IOException ex ) {
      LOGGER.log( Level.SEVERE, "Couldn't open file", ex );
    }
  }

  @Override
  public synchronized void addObserver( Observer o )
  {
    super.addObserver( o );
    observers.add( o );
  }

  @Override
  public synchronized void deleteObserver( Observer o )
  {
    super.deleteObserver( o );
    observers.remove( o );
  }

  @Override
  public void notifyObservers( Object arg )
  {
    super.notifyObservers( arg );
    for ( Observer observer : observers) {
      observer.update( this, arg );
    }
  }
}
