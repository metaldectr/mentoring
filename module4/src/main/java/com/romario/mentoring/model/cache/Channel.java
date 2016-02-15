package com.romario.mentoring.model.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * Channel model class
 */
public class Channel
{
  private final long id;
  private final  String title;
  private final  String desc;
  private final  List<Listing> listing;

  public Channel( final long id, final String title, final String desc, final List<Listing> listing )
  {
    this.id = id;
    this.title = title;
    this.desc = desc;
    this.listing = (listing == null) ? new ArrayList<Listing>() : listing;
  }

  public synchronized List<Listing> getListing()
  {
    return new ArrayList<Listing>(listing);
  }

  public synchronized void setListing(final List<Listing> listing )
  {
    if(listing == null) return;
    try{
      this.listing.addAll(listing);
    } catch( Exception e ) {
      System.out.println(e);
    }
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Channel)) return false;

    Channel channel = (Channel) o;

    return id == channel.id;

  }

  @Override
  public int hashCode() {
    return (int) (id ^ (id >>> 32));
  }
}
