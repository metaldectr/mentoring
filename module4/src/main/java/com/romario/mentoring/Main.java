package com.romario.mentoring;

import com.romario.mentoring.manager.Manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Main class
 */
public class Main
{
  public static void main( String[] args )
  {
    Manager manager = new Manager();

    manager.runCacheTask();

//    manager.runDeadlockTask();

//    manager.runLiveLockTask();
  }
}
