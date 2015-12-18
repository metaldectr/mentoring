package com.romario.mentoring.observer.listener;

import java.util.Observable;
import java.util.Observer;

/**
 * WordObserver
 */
public class WordObserver implements Observer
{
  private int countWords;

  @Override
  public void update( Observable o, Object arg )
  {
    String str = (String) arg;
    setCountWords( getCountWords()+1 );
  }

  public int getCountWords()
  {
    return countWords;
  }

  public void setCountWords( int countWords )
  {
    this.countWords = countWords;
  }
}
