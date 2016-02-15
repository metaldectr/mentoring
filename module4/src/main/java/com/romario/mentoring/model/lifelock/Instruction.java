package com.romario.mentoring.model.lifelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Intruction model class
 */
public class Instruction
{
  private static final Logger sLogger = LoggerFactory.getLogger(Instruction.class);
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
