package com.romario.mentoring.executor.writer;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.Listing;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * ListingWriterExecutor class
 */
public class ListingWriterExecutor
  implements Runnable
{
  private static final Logger sLogger = Logger.getLogger(
    ListingWriterExecutor.class.getName() );
  private static final Cache cache = Cache.getInstance();

  public void run()
  {
    sLogger.info(
      "ListingWriterExecutor start: " + Thread.currentThread().getId() );
    do {
      sLogger.info( Thread.currentThread().getId() + "run" );

        List<Channel> channels = cache.getChannelList();

        for( Channel channel : channels ) {
          List<Listing> tmpListings = channel.getListing();
          for( int j = 0; j < RandomUtil.randInt( 0, 5 ); j++ ) {
            if ( tmpListings == null ) {
              tmpListings = new ArrayList<Listing>();
            }
            tmpListings.add(
              new Listing( RandomUtil.nextLong(), RandomUtil.nextLong(),
                "listingTitle:" + RandomUtil.nextInt() ) );

          }
          channel.setListing( tmpListings );
        }
        cache.setChannelList( channels );

      try {
        Thread.sleep( 5 * 1000 );
      } catch( InterruptedException e ) {
        sLogger.error( "InterruptedException for thread sleep", e );
        //e.printStackTrace();
      }
    } while ( cache.isWriteFlag() );
  }

}
