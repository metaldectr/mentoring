package com.romario.mentoring.model;

/**
 * Ration model class
 */
public class Ratio
{
  private long listingId;
  private long ratio;

  public Ratio()
  {
  }

  public Ratio( long listingId, long ratio )
  {
    this.listingId = listingId;
    this.ratio = ratio;
  }

  public long getListingId()
  {
    return listingId;
  }

  public void setListingId( long listingId )
  {
    this.listingId = listingId;
  }

  public long getRatio()
  {
    return ratio;
  }

  public void setRatio( long ratio )
  {
    this.ratio = ratio;
  }
}
