package com.romario.mentoring.manager;

import com.romario.mentoring.executor.reader.ChannelReaderExecutor;
import com.romario.mentoring.executor.reader.ListingReaderExecutor;
import com.romario.mentoring.executor.reader.RatioReaderExecutor;
import com.romario.mentoring.executor.writer.ChannelWriterExecutor;
import com.romario.mentoring.executor.writer.ListingWriterExecutor;
import com.romario.mentoring.executor.writer.RatioWriterExecutor;
import com.romario.mentoring.model.Instruction;
import com.romario.mentoring.model.MyReader;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage application
 */
public class Manager
{
  private static final Logger sLogger = Logger.getLogger(
    Manager.class.getName() );

  public void runCacheTask()
  {
    List<Thread> threads = new ArrayList<Thread>();

    threads.add( thread("w-thread-1", new ChannelWriterExecutor()) );
    threads.add( thread("w-thread-2", new ListingWriterExecutor()) );
    threads.add( thread("w-thread-3", new RatioWriterExecutor()) );

    threads.add( thread("r-thread-1", new ChannelReaderExecutor() ) );
    threads.add( thread("r-thread-2", new ListingReaderExecutor() ) );
    threads.add( thread("r-thread-3", new RatioReaderExecutor() ) );

    for( Thread thread : threads ) {
      thread.start();
    }

    try {
      Thread.sleep( 120 * 1000 );
    } catch( InterruptedException e ) {
      sLogger.error( "InterruptedException ", e );
      e.printStackTrace();
    }
  }

  private static Thread thread(String name, Runnable r) {
    Thread t = new Thread(r);
    t.setName(name);
    return t;
  }

  public void runDeadlockTask()
  {
    final Object resource1 = "resource1";
    final Object resource2 = "resource2";
    final Object resource3 = "resource3";

    Thread t1 = new Thread()
    {
      @Override
      public void run()
      {
        synchronized( resource1 ) {
          System.out.println( "Do in sync1" );
          try {
            Thread.sleep( 50 );
          } catch( InterruptedException e ) {
            synchronized( resource2 ) {
              System.out.println( "Do in sync2" );
            }
          }
        }
        super.run();
      }
    };

    Thread t2 = new Thread()
    {
      @Override
      public void run()
      {
        synchronized( resource2 ) {
          System.out.println( "Do in sync2" );
          try {
            Thread.sleep( 50 );
          } catch( InterruptedException e ) {
            synchronized( resource3 ) {
              System.out.println( "Do in sync3" );
            }
          }
        }
        super.run();
      }
    };

    Thread t3 = new Thread()
    {
      @Override
      public void run()
      {
        synchronized( resource3 ) {
          System.out.println( "Do in sync3" );
          try {
            Thread.sleep( 50 );
          } catch( InterruptedException e ) {
            synchronized( resource1 ) {
              System.out.println( "Do in sync1" );
            }
          }
        }
        super.run();
      }
    };

    t1.start();
    t2.start();
    t3.start();
  }

  public void runLiveLockTask()
  {
    final MyReader myReader1 = new MyReader( "Reader1" );
    final MyReader myReader2 = new MyReader( "Reader2" );
    final Instruction instruction = new Instruction( myReader2 );

    Thread t1 = new Thread( new Runnable()
    {
      public void run()
      {
        myReader2.doPrint( instruction, myReader1 );
      }
    } );
    t1.setName("LiveLocked_1");

    Thread t2 = new Thread( new Runnable()
    {
      public void run()
      {
        myReader1.doPrint( instruction, myReader2 );
      }
    } );
    t2.setName("LiveLocked_2");
    t1.start();
    t2.start();
  }

}
