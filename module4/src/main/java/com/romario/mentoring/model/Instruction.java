package com.romario.mentoring.model;

/**
 * Intruction model class
 */
public class Instruction
{
  private MyReader reader;

  public Instruction( MyReader reader )
  {
    this.reader = reader;
  }

  public MyReader getReader()
  {
    return reader;
  }

  public void setReader( MyReader reader )
  {
    this.reader = reader;
  }

  public synchronized void use()
  {
    System.out.printf( "InstructionUse" );
  }
}
