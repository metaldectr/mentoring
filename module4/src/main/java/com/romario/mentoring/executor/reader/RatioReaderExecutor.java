package com.romario.mentoring.executor.reader;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.Listing;
import com.romario.mentoring.model.Ratio;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * RatioReaderExecutor class
 */
public class RatioReaderExecutor
  implements Runnable
{
  private static final Logger sLogger = Logger.getLogger(
    RatioReaderExecutor.class.getName() );
  private final Cache cache = Cache.getInstance();

  public void run()
  {
    sLogger.info( "RationReaderExecutor start: " + Thread.currentThread().getId() );
    final Lock lock = cache.getLock();
    do {
/*      try {
         lock.lock();*/

          sLogger.info( "RationReaderExecutor run" );
          List<Channel> channels = new ArrayList<Channel>( cache.getChannelList() );
          List<Ratio> ratioList = new ArrayList<Ratio>();

          for( Channel channel : channels ) {
            if ( channel.getListing() != null ) {
              for( Listing listing : new ArrayList<Listing>( channel.getListing() ) ) {
                ratioList.add( listing.getRatio() );
              }
            }
          }

          List<Ratio> topRatioList = getTopRatioList( new ArrayList<Ratio>( ratioList ) );
          for( int i = 0; i < 10; i++ ) {
            if ( topRatioList != null && !topRatioList.isEmpty() &&
              topRatioList.size() > 10 && topRatioList.get( i ) != null ) {
              long listingId = topRatioList.get( i ).getListingId();
              Channel channel = findChannelById( channels, listingId );
              Listing listing = findListingById( channels, listingId );
              if ( channel != null && listing.getRatio() != null ) {
                sLogger.info( "RThread3. Channel: " + channel.getTitle() + ". Listing: " +
                  listing.getTitle() + ". Ratio: " +
                  listing.getRatio().getRatio() );
                /*System.out.println(
                  "RThread3. Channel: " + channel.getTitle() + ". Listing: " +
                    listing.getTitle() + ". Ratio: " +
                    listing.getRatio().getRatio() );*/
              }
            }
          }

/*      } finally {
        lock.unlock();
      }*/
      try {
        Thread.sleep( RandomUtil.randInt( 2, 4 ) * 1000 );
      } catch( InterruptedException e ) {
        sLogger.error( "InterruptedException ", e );
        //e.printStackTrace();
      }
    } while ( cache.isReadFlag() );

  }

  private Listing findListingById( List<Channel> channelList, long listingId )
  {
    for( Channel channel : channelList ) {
      if ( channel.getListing() != null && !channel.getListing().isEmpty() ) {
        for( Listing listing : channel.getListing() ) {
          if ( listing.getId() == listingId ) {
            return listing;
          }
        }
      }
    }
    return null;
  }

  private Channel findChannelById( List<Channel> channelList, long listingId )
  {
    for( Channel channel : channelList ) {
      if ( channel.getListing() != null && !channel.getListing().isEmpty() ) {
        for( Listing listing : channel.getListing() ) {
          if ( listing.getId() == listingId ) {
            return channel;
          }
        }
      }
    }
    return null;
  }

  private List<Ratio> getTopRatioList( List<Ratio> ratioList )
  {
    Collections.sort( ratioList, new Comparator<Ratio>()
      {
        public int compare( Ratio o1, Ratio o2 )
        {
          if (o1 == null || o2 == null) {
            return 0;
          }
          if ( o1.getRatio() < o2.getRatio() ) {
            return -1;
          } else if ( o1.getRatio() > o2.getRatio() ) {
            return 1;
          } else {
            return 0;
          }
        }
      }
    );
    return ratioList;
  }


}
