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
    sLogger.info("Started");

    do {
      sLogger.info("Run");
      List<Channel> channels = cache.getChannelList();
      List<Ratio> ratioList = new ArrayList<Ratio>();

      for( Channel channel : channels ) {
        if ( channel.getListing() != null ) {
          for( Listing listing : channel.getListing() ) {
            ratioList.add( listing.getRatio() );
          }
        }
      }
      for( int i = 0; i < 10; i++ ) {
        List<Ratio> topRatioList = getTopRatioList( ratioList );
        if ( topRatioList != null && !topRatioList.isEmpty() &&
          topRatioList.size() > 10 && topRatioList.get( i ) != null ) {
          long listingId = topRatioList.get( i ).getListingId();
          Channel channel = findChannelById( channels, listingId );
          Listing listing = findListingById( channels, listingId );
          if ( channel != null && listing.getRatio() != null ) {
            String title = channel.getTitle();
            long ratio = listing.getRatio().getRatio();
            sLogger.info(String.format("{chanelTitle:'%s', ratio:'%s'}", title, ratio));
          }
        }
      }
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
