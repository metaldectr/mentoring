package com.romario.mentoring.executor.writer;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.Listing;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

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
      List<Channel> channels = new ArrayList<Channel>( cache.getChannelList() );
/*      try {
        if (cache.getLock().tryLock()) {*/
          int countIter = channels.size();
          for( int i = 0; i < countIter; i++ ) {
            Channel channel = channels.get( i );
            int count = RandomUtil.randInt( 1, 2 );
            List<Listing> tmpListings = channel.getListing();
            for( int j = 0; j < count; j++ ) {
              if ( tmpListings == null ) {
                tmpListings = new ArrayList<Listing>(count);
              }
              tmpListings.add(
                new Listing( RandomUtil.randInt( 1, 3 ), RandomUtil.nextLong(),
                  "listingTitle:" + RandomUtil.nextInt(), null ) );
            }

            channel.setListing( tmpListings );
          }
          cache.setChannelList( channels );
/*        }
      } finally {
        cache.getLock().unlock();
      }*/

      try {
        Thread.sleep( 5 * 1000 );
      } catch( InterruptedException e ) {
        sLogger.error( "InterruptedException for thread sleep", e );
        //e.printStackTrace();
      }
    } while ( cache.isWriteFlag() );
  }

}
