package com.romario.mentoring.model.cache;

import java.util.*;

/**
 * Cache model class
 */
public class Cache
{
  private static volatile Cache instance;

  private final List<Channel> channelList;

  private Cache()
  {
    channelList = new ArrayList<Channel>();
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

  public synchronized List<Channel> getChannelList()
  {
    return new ArrayList<Channel>(channelList);
  }

  public synchronized void setChannelList(final List<Channel> channelList )
  {
    this.channelList.addAll( channelList );
  }
}
