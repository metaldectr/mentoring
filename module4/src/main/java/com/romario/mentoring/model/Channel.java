package com.romario.mentoring.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Channel model class
 */
public final class Channel
{
  private final long id;
  private final long uid = System.nanoTime();
  private final String title;
  private final String desc;
  private final List<Listing> listing;

  public Channel( long id, String title, String desc,
    List<Listing> listing )
  {
    this.id = id;
    this.title = title;
    this.desc = desc;
    if (listing == null) {
      this.listing = new ArrayList<Listing>(  );
    } else {
      this.listing = listing;
    }
  }

  public synchronized List<Listing> getListing()
  {
    return listing;
  }

  public long getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDesc()
  {
    return desc;
  }

  public long getUid()
  {
    return uid;
  }

  @Override
  public boolean equals( Object obj )
  {
    if ( obj == this ) {
      return true;
    }
    if ( obj == null || obj.getClass() != this.getClass() ) {
      return false;
    }
    Channel channel = (Channel)obj;
    return id == channel.id && ( title == channel.getTitle() ||
      ( desc == channel.getDesc() && listing.equals( channel.getListing() ) ) );

  }

  @Override
  public int hashCode()
  {
    final int simpleNumber = 13;
    int hash = 1;

    hash = (int)(simpleNumber * hash + id);
    hash = simpleNumber * hash + ((title == null) ? 0 : title.hashCode());
    hash = simpleNumber * hash + ((desc == null) ? 0 : desc.hashCode());
    hash = simpleNumber * hash + ((listing == null) ? 0 : listing.hashCode());

    return hash;
  }
}
