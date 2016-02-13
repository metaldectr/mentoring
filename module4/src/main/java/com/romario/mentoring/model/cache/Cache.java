package com.romario.mentoring.model.cache;

import com.romario.mentoring.model.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cache model class
 */
public class Cache
{
  private static volatile Cache instance;

  private volatile List<Channel> channelList;
  private volatile Map<Long, Channel> maps;

  private volatile Lock lock = new ReentrantLock(  );

  private volatile boolean readFlag;
  private volatile boolean writeFlag;

  private Cache()
  {
    channelList = new ArrayList<Channel>();
    readFlag = true;
    writeFlag = true;
    maps = new HashMap<Long, Channel>(  );
  }

  public static Cache getInstance()
  {
    Cache localInstance = instance;
    if ( localInstance == null ) {
      synchronized( Cache.class ) {
        localInstance = instance;
        if ( localInstance == null ) {
          instance = localInstance = new Cache();
        }
      }
    }
    return localInstance;
  }

  public synchronized List<Channel> getFromCache() {
    return channelList;
  }

  public boolean isReadFlag()
  {
    return readFlag;
  }

  public void setReadFlag( boolean readFlag )
  {
    this.readFlag = readFlag;
  }

  public boolean isWriteFlag()
  {
    return writeFlag;
  }

  public void setWriteFlag( boolean writeFlag )
  {
    this.writeFlag = writeFlag;
  }

  public synchronized List<Channel> getChannelList()
  {
    return new ArrayList<Channel>( channelList );
  }

  public synchronized void setChannelList(
    List<Channel> channelList )
  {
    for (Channel channel : this.channelList) {
      this.maps.put( channel.getId(), channel );
    }

    /*this.channelList.addAll( channelList );*/
    final List<Channel> channels = new ArrayList<Channel>(  );
    for (Channel channel : channelList) {
      if (!this.channelList.contains( channel )) {
        channels.add( channel );
      } else {
        for (int i = 0; i < this.channelList.size(); i++) {
          if (this.channelList.get( i ).getId() == channel.getId()) {
            channels.add( channel );
            this.channelList.remove( i );
          }
        }
      }
    }
    this.channelList.addAll( channels );


  }

  public Lock getLock()
  {
    return lock;
  }

  public void setLock( Lock lock )
  {
    this.lock = lock;
  }
}
