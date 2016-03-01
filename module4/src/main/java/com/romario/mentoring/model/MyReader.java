package com.romario.mentoring.model;

import org.apache.log4j.Logger;

/**
 * MyReader model class
 */
public class MyReader
{
  private static final Logger LOG = Logger.getLogger(MyReader.class);
  private volatile boolean flag = true;
  private volatile String name;

  public MyReader( String name )
  {
    this.name = name;
  }

  public void doPrint( Instruction instruction, MyReader that )
  {

    while ( this.flag ) {
      if ( instruction.getReader() != this ) {
        try {
          Thread.sleep( 1 );
        } catch( InterruptedException e ) {
          continue;
        }
        continue;
      }

      if ( that.flag ) {
        LOG.info(String.format("Give priority to : %s%n", this.name));
        instruction.setReader( that );
        continue;
      }

      instruction.use();
      this.flag = false;
      LOG.info(String.format("Instruction was used. Give priority to : %s%n", name));
      instruction.setReader( that );
    }
  }
}
