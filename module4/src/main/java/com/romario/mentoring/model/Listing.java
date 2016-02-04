package com.romario.mentoring.model;

/**
 * Listing model class
 */
public class Listing
{

  private long id;
  private long channelId;
  private String title;
  private Ratio ratio;

  public Listing()
  {
  }

  public Listing( long id, long channelId, String title,
    Ratio ratio )
  {
    this.id = id;
    this.channelId = channelId;
    this.title = title;
    this.ratio = ratio;
  }

  public Ratio getRatio()
  {
    return ratio;
  }

  public void setRatio( Ratio ratio )
  {
    this.ratio = ratio;
  }

  public Listing( long id, long channelId, String title )
  {
    this.id = id;
    this.channelId = channelId;
    this.title = title;
  }

  public long getId()
  {
    return id;
  }

  public void setId( long id )
  {
    this.id = id;
  }

  public long getChannelId()
  {
    return channelId;
  }

  public void setChannelId( long channelId )
  {
    this.channelId = channelId;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }
}
