package com.romario.mentoring.model;

/**
 * Listing model class
 */
public final class Listing
{

  private final long id;
  private final long channelId;
  private final String title;
  private final Ratio ratio;

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

  public long getId()
  {
    return id;
  }

  public long getChannelId()
  {
    return channelId;
  }

  public String getTitle()
  {
    return title;
  }

}
