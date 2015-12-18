package com.romario.mentoring.observer.main;

import com.romario.mentoring.observer.listener.NumberObserver;
import com.romario.mentoring.observer.listener.WordObserver;
import com.romario.mentoring.observer.reader.MyFileReader;

/**
 * Main class
 */
public class Main
{

  public static void main( String[] args )
  {
    MyFileReader myFileReader = new MyFileReader();
    final NumberObserver no = new NumberObserver();
    final WordObserver wo = new WordObserver();
    String path = "test.txt";
    myFileReader.addObserver( no );
    myFileReader.addObserver( wo );
    myFileReader.readFile( path );

    System.out.println( no.getCountNumbers() );
    System.out.println( wo.getCountWords() );
  }

}
