package com.romario.mentoring.manager;

import com.romario.mentoring.model.lifelock.Instruction;
import com.romario.mentoring.model.lifelock.MyReader;
import com.romario.mentoring.runnable.deadlock.DeadLockThread;
import com.romario.mentoring.runnable.lifelock.LifeLockThread;
import com.romario.mentoring.runnable.cache.reader.ChannelReaderExecutor;
import com.romario.mentoring.runnable.cache.reader.ListingReaderExecutor;
import com.romario.mentoring.runnable.cache.reader.RatioReaderExecutor;
import com.romario.mentoring.runnable.cache.writer.ChannelWriterExecutor;
import com.romario.mentoring.runnable.cache.writer.ListingWriterExecutor;
import com.romario.mentoring.runnable.cache.writer.RatioWriterExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.MINUTES;
import static com.romario.mentoring.util.ThreadUtil.sleep;


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
    List<Thread> threads = new ArrayList<Thread>();

    threads.add( new Thread( new ChannelWriterExecutor(),   "WriteThread_1_Channel" ) );
    threads.add( new Thread( new ListingWriterExecutor(),   "WriteThread_2_Listing" ) );
    threads.add( new Thread( new RatioWriterExecutor(),     "WriteThread_3_Ratio" ) );
    threads.add( new Thread( new ChannelReaderExecutor(),   "ReadThread_1_Channel" ) );
    threads.add( new Thread( new ListingReaderExecutor(),   "ReadThread_2_Listing" ) );
    threads.add( new Thread( new RatioReaderExecutor(),     "ReadThread_3_Ratio" ) );

    for( Thread thread : threads ) {
      thread.start();
    }

    sleep(5, MINUTES);
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
