package com.romario.mentoring.observer.listener;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * NumberObserver
 */
public class NumberObserver implements Observer
{
  private int countNumbers;
  @Override
  public void update( Observable o, Object arg )
  {
    String str = (String) arg;
    str = str.replaceAll( "[^-?0-9]+", " " );
    List<String> strs = Arrays.asList( str.trim().split( " " ) );
    if ( isNumeric( strs.get( 0 ) )) {
      setCountNumbers( getCountNumbers() + strs.size() );
    }

  }

  public boolean isNumeric(String s) {
    return java.util.regex.Pattern.matches("\\d+", s);
  }

  public int getCountNumbers()
  {
    return countNumbers;
  }

  public void setCountNumbers( int countNumbers )
  {
    this.countNumbers = countNumbers;
  }
}
