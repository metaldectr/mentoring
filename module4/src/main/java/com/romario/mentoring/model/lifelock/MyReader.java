package com.romario.mentoring.model.lifelock;

import org.apache.log4j.Logger;

import static com.romario.mentoring.util.ThreadUtil.MILLISECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleep;
/**
 * MyReader model class
 */
public class MyReader
{
  private static final Logger sLogger = Logger.getLogger(MyReader.class);
  private volatile boolean shouldPrint = true;
  private final String name;

  public MyReader( final String name )
  {
    this.name = name;
  }

  public void doPrint(Instruction instruction, MyReader another )
  {

    while ( this.shouldPrint ) {

      if ( instruction.reader != this ) {
        sleep(1, MILLISECONDS);
        continue;
      }

      if ( another.shouldPrint ) {
        sLogger.info("Give a way to: " + another.name);
        instruction.reader = another;
        continue;
      }

      instruction.use();
      this.shouldPrint = false;
      sLogger.info("Instruction is used by: " + this.name);
      instruction.reader = another;
    }
  }
}
