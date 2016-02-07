package com.romario.mentoring.model;

import org.apache.log4j.Logger;

/**
 * Intruction model class
 */
public class Instruction
{
  private static final Logger LOG = Logger.getLogger(Instruction.class);

  private MyReader reader;

  public Instruction( MyReader reader )
  {
    this.reader = reader;
  }

  public synchronized MyReader getReader()
  {
    return reader;
  }

  public synchronized void setReader( MyReader reader )
  {
    this.reader = reader;
  }

  public synchronized void use()
  {
    LOG.info( "Instruction was used" );
  }
}
