package com.romario.mentoring.model.cache;

/**
 * Listing model class
 */
public class Listing
{

  private final long id;
  private final long channelId;
  private final String title;
  private final Ratio ratio;

  public Listing( final long id, final long channelId, final String title, final Ratio ratio )
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
