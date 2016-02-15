package com.romario.mentoring.model.lifelock;

import org.apache.log4j.Logger;

/**
 * Intruction model class
 */
public class Instruction
{
  private static final Logger sLogger = Logger.getLogger(Instruction.class);
  public volatile MyReader reader;

  public Instruction( MyReader reader )
  {
    this.reader = reader;
  }

  public synchronized void use()
  {
    sLogger.info( "Instruction is used " );
  }
}
