package com.romario.mentoring.model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Channel model class
 */
public class Channel
{
  private long id;
  private String title;
  private String desc;
  private List<Listing> listing;

  public Channel( long id, String title, String desc,
    List<Listing> listing )
  {
    this.id = id;
    this.title = title;
    this.desc = desc;
    this.listing = listing;
  }

  public synchronized List<Listing> getListing()
  {
    return listing;
  }

  public synchronized void setListing( List<Listing> listing )
  {
    if (this.listing == null) {
      this.listing = new ArrayList<Listing>(  );
    }
    try{
    for (Listing listing1 : listing) {
      this.listing.add( listing1 );
    }

    } catch( Exception e ) {
      System.out.println("s");
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
}
