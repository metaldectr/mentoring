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
    sLogger.info("Started");

    do {
      sLogger.info("Run");
      List<Channel> channels = cache.getChannelList();

      for( Channel channel : channels ) {
        String title = channel.getTitle();
        String desc = channel.getDesc();
        sLogger.info(String.format("{chanelTitle:'%s', description:'%s'}", title, desc));
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
