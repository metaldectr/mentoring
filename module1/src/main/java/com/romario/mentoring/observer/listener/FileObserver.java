package com.romario.mentoring.observer.listener;

import java.util.Observable;
import java.util.Observer;

/**
 * File Observer
 */
public class FileObserver
  implements Observer
{

  private int countWords;
  private int countNumbers;


  @Override
  public void update( Observable o, Object arg )
  {
    if ( ((int)arg) == 1 ) {
      countNumbers++;
    } else {
      countWords++;
    }
  }

  public int getCountWords()
  {
    return countWords;
  }

  public void setCountWords( int countWords )
  {
    this.countWords = countWords;
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
