package com.romario.mentoring.model.cache;

/**
 * Ration model class
 */
public class Ratio
{
  private final long listingId;
  private final long ratio;

  public Ratio( final long listingId, final long ratio )
  {
    this.listingId = listingId;
    this.ratio = ratio;
  }

  public long getListingId()
  {
    return listingId;
  }

  public long getRatio()
  {
    return ratio;
  }

}
