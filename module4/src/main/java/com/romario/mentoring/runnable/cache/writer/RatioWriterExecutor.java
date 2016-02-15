package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Ratio;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

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
    sLogger.info(
      "RationWriterExecutor start: " + Thread.currentThread().getId() );
    do {
      sLogger.info(
        Thread.currentThread().getId() + " run" );
      List<Channel> channels = cache.getChannelList();
          for( Channel channel : channels ) {
            List<Listing> listings = channel.getListing();
            if ( listings != null && !listings.isEmpty() ) {
              for( int i = 0; i < listings.size(); i++ ) {
                Listing listing = listings.get( i );
                if (listing != null) {
                  if ( listing.getRatio() != null ) {
                    Ratio ratio = listing.getRatio();
                    listings.add(
                      i, new Listing( listing.getId(), listing.getChannelId(),
                        listings.get( i ).getTitle(),
                        new Ratio( ratio.getListingId(), ratio.getRatio() ) ) );
                  } else {
                    listings.add(
                      i, new Listing( listing.getId(), listing.getChannelId(),
                        listing.getTitle(),
                        new Ratio(
                          RandomUtil.nextLong(), RandomUtil.nextLong() ) ) );
                  }
                }
              }
            }
            cache.setChannelList( channels );
          }
      try {
        Thread.sleep( RandomUtil.randInt( 1, 2 ) * 1000 );
      } catch( InterruptedException ignore ) {}
    } while ( true );

  }
}
