package com.romario.mentoring.model.cache;

import java.util.*;

/**
 * Channel model class
 */
public class Channel {
    private final long id;
    private final String title;
    private final String desc;
    private final Set<Listing> listings;

    public Channel(final long id, final String title, final String desc) {
        this(id, title, desc, new HashSet<Listing>());
    }

    public Channel(final long id, final String title, final String desc, final Set<Listing> listings) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.listings = (listings == null) ? new HashSet<Listing>() : listings;
    }

    public List<Listing> getListings() {
        return new ArrayList<Listing>(listings);
    }

    public void addListings(final Collection<Listing> listings) {
        if (listings == null) return;
        this.listings.addAll(listings);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
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

    @Override
    public String toString() {
        return "Channel {" +
                "id: " + id +
                ", title: '" + title + '\'' +
                ", desc: '" + desc + '\'' +
                '}';
    }
}
