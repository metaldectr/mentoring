package com.romario.mentoring.observer.main;

import com.romario.mentoring.observer.listener.FileObserver;
import com.romario.mentoring.observer.reader.MyFileReader;

/**
 * Main class
 */
public class Main
{

  public static void main( String[] args )
  {
    MyFileReader myFileReader = new MyFileReader();
    final FileObserver fo = new FileObserver();
    String path = "test.txt";
    myFileReader.addObserver( fo );
    myFileReader.readFile( path );

    System.out.println( fo.getCountNumbers() );
    System.out.println( fo.getCountWords() );
  }

}
