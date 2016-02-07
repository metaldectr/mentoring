package com.romario.mentoring.executor.reader;

import com.romario.mentoring.model.Channel;
import com.romario.mentoring.model.Listing;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ListingReaderExecutor class
 */
public class ListingReaderExecutor
  implements Runnable
{
  private static final Logger sLogger = Logger.getLogger(
    ListingReaderExecutor.class.getName() );
  private final Cache cache = Cache.getInstance();

  public void run()
  {
    sLogger.info("Started");

    do {
      sLogger.info("Run");
      List<Channel> channels = cache.getChannelList();
      Map<Long, Channel> channelsMap = calculateAverageRatio( channels );
      SortedSet<Long> keys = new TreeSet<Long>( channelsMap.keySet() );
      Iterator<Long> iterator = keys.iterator();
      for( int i = 0; i < 3; i++ ) {
        if ( iterator.hasNext() ) {
          Long key = iterator.next();
          String title = channelsMap.get(key).getTitle();
          String desc = channelsMap.get(key).getDesc();
          sLogger.info(String.format("{chanelTitle:'%s', description:'%s'}", title, desc));
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

  private Map<Long, Channel> calculateAverageRatio( List<Channel> channels )
  {
    Map<Long, Channel> averageRatioMap = new HashMap<Long, Channel>();
    for( Channel channel : channels ) {
      if ( channel.getListing() != null ) {
        if ( channel.getListing() != null && !channel.getListing().isEmpty() ) {
          long averageRatio = calculateAverage( channel.getListing() );
          averageRatioMap.put( averageRatio, channel );
        }
      }
    }
    return averageRatioMap;
  }


  private long calculateAverage( List<Listing> listings )
  {
    long count = 0;
    long sum = 0;
    for( Listing listing : listings ) {
      if ( listing.getRatio() != null ) {
        sum += listing.getRatio().getRatio();
        count++;
      }
    }
    if ( count != 0 ) {
      return sum / count;
    } else {
      return 1;
    }
  }

}
