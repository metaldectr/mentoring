package com.romario.mentoring.manager;

import com.romario.mentoring.model.lifelock.Instruction;
import com.romario.mentoring.model.lifelock.MyReader;
import com.romario.mentoring.runnable.cache.reader.ChannelReader;
import com.romario.mentoring.runnable.cache.reader.ListingReader;
import com.romario.mentoring.runnable.cache.reader.RatioReader;
import com.romario.mentoring.runnable.cache.writer.ChannelWriter;
import com.romario.mentoring.runnable.cache.writer.ListingWriter;
import com.romario.mentoring.runnable.cache.writer.RatioWriter;
import com.romario.mentoring.runnable.deadlock.DeadLockThread;
import com.romario.mentoring.runnable.lifelock.LifeLockThread;

import static com.romario.mentoring.util.ThreadUtil.MINUTES;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;


/**
 * Manage application
 */
public class Manager
{
  /**
   * Task 1
   */
  public void runCacheTask()
  {
    new ChannelWriter(  "W_Thread_1_Channel" ).start();
    new ListingWriter(  "W_Thread_2_Listing" ).start();
    new RatioWriter(    "W_Thread_3_Ratio"   ).start();
    new ChannelReader(  "R_Thread_1_Channel" ).start();
    new ListingReader(  "R_Thread_2_Listing" ).start();
    new RatioReader(    "R_Thread_3_Ratio"   ).start();

    sleepFor(5, MINUTES);
  }

  /**
   * Task 2
   */
  public void runDeadlockTask()
  {
    final Object resource1 = "resource1";
    final Object resource2 = "resource2";
    final Object resource3 = "resource3";

    new DeadLockThread("DeadLockedThread_1")
            .with(resource1, resource2)
            .start();
    new DeadLockThread("DeadLockedThread_2")
            .with(resource2, resource3)
            .start();
    new DeadLockThread("DeadLockedThread_3")
            .with(resource3, resource1)
            .start();
  }

  /**
   * Task 3
   */
  public void runLiveLockTask()
  {
    final MyReader reader1 = new MyReader( "Reader1" );
    final MyReader reader2 = new MyReader( "Reader2" );
    final Instruction instruction = new Instruction( reader1 );

    new LifeLockThread("LifeLockedThread_1")
            .withReaders(reader1, reader2)
            .withInstruction(instruction)
            .start();
    new LifeLockThread("LifeLockedThread_2")
            .withReaders(reader2, reader1)
            .withInstruction(instruction)
            .start();
  }
}
