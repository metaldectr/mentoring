package com.romario.mentoring.facade.main;

import com.romario.mentoring.facade.processor.PersonProcessor;
import com.romario.mentoring.facade.processor.PersonProcessorInterface;

/**
 * Main class
 */
public class Main
{
  public static void main( String[] args )
  {
    PersonProcessorInterface p1Processor = new PersonProcessor( "person1", 23, 100 );
    PersonProcessorInterface p2Processor = new PersonProcessor( "person2", 21, 101 );
  }

}
