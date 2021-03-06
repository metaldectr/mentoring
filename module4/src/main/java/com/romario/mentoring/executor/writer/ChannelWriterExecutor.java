package com.romario.mentoring.executor.writer;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ChannelWriterExecutor class
 */
public class ChannelWriterExecutor
  implements Runnable
{
  private static final Logger sLogger = Logger.getLogger(
    ChannelWriterExecutor.class.getName() );
  private static final Cache cache = Cache.getInstance();

  public static final int MIN = 100;
  public static final int MAX = 150;

  public void run()
  {
    sLogger.info(
      "CahnnelWriterExecutor start: " + Thread.currentThread().getId() );
    do {
      sLogger.info(
        Thread.currentThread().getId() + " run" );

      List<Channel> tmpChannels = new ArrayList<Channel>();
      int count = RandomUtil.randInt( MIN, MAX );
      for( int i = 0; i < count; i++ ) {
        tmpChannels.add( new Channel(
          RandomUtil.randInt( 1, 500 ),
          "channel:" + RandomUtil.nextInt(),
          "description:" + RandomUtil.nextInt(), null ) );
      }
      cache.setChannelList( tmpChannels );


      try {
        Thread.sleep( 20 * 1000 );
      } catch( InterruptedException e ) {
        sLogger.error( "InterruptedException ", e );
      }
    } while ( cache.isWriteFlag() );
  }
}
