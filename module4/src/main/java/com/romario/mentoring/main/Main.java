package com.romario.mentoring.main;

import com.romario.mentoring.manager.Manager;

/**
 * Main class
 */
public class Main
{
  public static void main( String[] args )
  {
    Manager manager = new Manager();
    //manager.runCacheTask();

    manager.runDeadlockTask();

    manager.runLiveLockTask();
  }
}
