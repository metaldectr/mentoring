package com.romario.mentoring.model.cache;

import com.romario.mentoring.model.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Cache model class
 */
public class Cache
{
  private static volatile Cache instance;

  private List<Channel> channelList;

  private volatile boolean readFlag;
  private volatile boolean writeFlag;

  private Cache()
  {
    channelList = new ArrayList<Channel>();
    readFlag = true;
    writeFlag = true;
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

  public synchronized void putInCache() {

  }

  /*public synchronized List<Channel> getFromCache() {

  }*/

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

  public List<Channel> getChannelList()
  {
    return channelList;
  }

  public void setChannelList(
    List<Channel> channelList )
  {
    this.channelList = channelList;
  }

}
