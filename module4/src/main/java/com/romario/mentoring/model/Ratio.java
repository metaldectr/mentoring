package com.romario.mentoring.model;

/**
 * Ration model class
 */
public final class Ratio
{
  private final long listingId;
  private final long ratio;

  public Ratio( long listingId, long ratio )
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
