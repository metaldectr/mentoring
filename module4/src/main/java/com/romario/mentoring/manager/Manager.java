package com.romario.mentoring.manager;

import com.romario.mentoring.executor.reader.ChannelReaderExecutor;
import com.romario.mentoring.executor.reader.ListingReaderExecutor;
import com.romario.mentoring.executor.reader.RatioReaderExecutor;
import com.romario.mentoring.executor.writer.ChannelWriterExecutor;
import com.romario.mentoring.executor.writer.ListingWriterExecutor;
import com.romario.mentoring.executor.writer.RatioWriterExecutor;
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
    threads.add( new Thread( new ChannelWriterExecutor() ) );
    threads.add( new Thread( new ListingWriterExecutor() ) );
    threads.add( new Thread( new RatioWriterExecutor() ) );
    threads.add( new Thread( new ChannelReaderExecutor() ) );
    threads.add( new Thread( new ListingReaderExecutor() ) );
    threads.add( new Thread( new RatioReaderExecutor() ) );

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
    final boolean flag = true;
    final Object resource2 = "resource2";


    class MyReader
      implements Runnable
    {
      public void run()
      {
        while ( true ) {
          synchronized( resource2 ) {
            try {
              if ( flag ) {
                doPrint();
                resource2.wait( 200 );
              }
              Thread.sleep( 1000 );
              resource2.notify();
            } catch( InterruptedException e ) {
              e.printStackTrace();
            }
          }
        }
      }

      public void doPrint()
      {
        System.out.println( "Do Print" );
      }
    }

    Thread t1 = new Thread( new MyReader() );
    Thread t2 = new Thread( new MyReader() );

    t1.start();
    t2.start();
  }

}
