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
      //e.printStackTrace();
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
            synchronized( resource2 ) {
              System.out.println( "Do in sync2" );
            }
          } catch( InterruptedException e ) {
            sLogger.error( "InterruptedException ", e );
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
            synchronized( resource3 ) {
              System.out.println( "Do in sync3" );
            }
          } catch( InterruptedException e ) {
            sLogger.error( "InterruptedException ", e );
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
            synchronized( resource1 ) {
              System.out.println( "Do in sync1" );
            }
          } catch( InterruptedException e ) {
            sLogger.error( "InterruptedException ", e );
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
    final MyReader myReader = new MyReader( "Reader1" );
    final MyReader myReader1 = new MyReader( "Reader2" );
    final Instruction instruction = new Instruction( myReader );

    new Thread( new Runnable()
    {
      public void run()
      {
        myReader.doPrint( instruction, myReader );
      }
    } ).start();

    new Thread( new Runnable()
    {
      public void run()
      {
        myReader1.doPrint( instruction, myReader1 );
      }
    } ).start();

  }

}
