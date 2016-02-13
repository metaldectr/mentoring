package com.romario.mentoring.executor.reader;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * ChannelReaderExecutor class
 */
public class ChannelReaderExecutor
  implements Runnable
{
  private static final Logger sLogger = Logger.getLogger(
    ChannelReaderExecutor.class.getName() );
  private static final Cache cache = Cache.getInstance();

  public void run()
  {
    sLogger.info( "ChannelReaderExecutor start: " + Thread.currentThread().getId() );

    do {
      sLogger.info( "ChannelReaderExecutor run" );
      List<Channel> channels = cache.getChannelList();

      for( Channel channel : channels ) {
        sLogger.info( "RThread1. ChannelID: " + channel.getId() + " ChannelTitle: " + channel.getTitle() + ",  Description: " +
          channel.getDesc() );
        /*System.out.println(
          "RThread1. ChannelID: " + channel.getId() + " ChannelTitle: " + channel.getTitle() + ",  Description: " +
            channel.getDesc() );*/
      }

      try {
        Thread.sleep( RandomUtil.randInt( 2, 4 ) * 1000 );
      } catch( InterruptedException e ) {
        sLogger.error( "InterruptedException ", e );
        //e.printStackTrace();
      }
    } while ( cache.isReadFlag() );
  }
}
