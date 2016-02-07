package com.romario.mentoring.executor.writer;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.Listing;
import com.romario.mentoring.model.Ratio;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * RatioWriterExecutor class
 */
public class RatioWriterExecutor
  implements Runnable
{
  private static final Logger sLogger = Logger.getLogger(
    RatioWriterExecutor.class.getName() );
  private static final Cache cache = Cache.getInstance();

  public void run()
  {
    sLogger.info("Started");
    do {
      sLogger.info("Run");
      List<Channel> channels = cache.getChannelList();
      List<Ratio> tmpRatioList = new ArrayList<Ratio>();
      for( Channel channel : channels ) {
        List<Listing> listings = channel.getListing();
        if ( listings != null && !listings.isEmpty() ) {
          for( Listing listing : listings ) {
            if ( listing.getRatio() != null ) {
              Ratio ratio = listing.getRatio();
              ratio.setRatio( ratio.getRatio() );
            } else {
              listing.setRatio(
                new Ratio( RandomUtil.nextLong(), RandomUtil.nextLong() ) );
            }
          }
        }
        cache.setChannelList( channels );

      }
      try {
        Thread.sleep( RandomUtil.randInt( 1, 2 ) * 1000 );
      } catch( InterruptedException e ) {
        sLogger.error( "InterruptedException for thread sleep", e );
        //e.printStackTrace();
      }
    } while ( cache.isWriteFlag() );

  }
}
