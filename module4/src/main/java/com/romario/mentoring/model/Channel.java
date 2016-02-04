package com.romario.mentoring.model;

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

  public Channel()
  {
  }

  public Channel( long id, String title, String desc,
    List<Listing> listing )
  {
    this.id = id;
    this.title = title;
    this.desc = desc;
    this.listing = listing;
  }

  public List<Listing> getListing()
  {
    return listing;
  }

  public void setListing( List<Listing> listing )
  {
    this.listing = listing;
  }

  public Channel( long id, String title, String desc )
  {
    this.id = id;
    this.title = title;
    this.desc = desc;
  }

  public long getId()
  {
    return id;
  }

  public void setId( long id )
  {
    this.id = id;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getDesc()
  {
    return desc;
  }

  public void setDesc( String desc )
  {
    this.desc = desc;
  }
}
