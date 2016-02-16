package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import com.romario.mentoring.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import static com.romario.mentoring.util.RandomUtil.randString;
import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ListingWriter class
 */
public class ListingWriter extends AbstractCacheExecutor {
    public static int id = 1;
    public static final int MIN = 1;
    public static final int MAX = 3;

    public ListingWriter(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        for (Channel channel : cache.getChannels()) {
            int listingsNumber = RandomUtil.randInt(MIN, MAX);
            List<Listing> listings = channel.getListings();
            listings.addAll(createListings(listingsNumber));

            LOG.info("{ channelID: {}, generatedListings: {} }", channel.getId(), listingsNumber);
            cache.putChannel(new Channel(
                    channel.getId(),
                    channel.getTitle(),
                    channel.getDesc(),
                    listings
            ));
        }

        sleepFor(2, SECONDS);
    }

    private List<Listing> createListings(final int listingsNumber) {
        final List<Listing> generatedChannels = new ArrayList<Listing>();

        for (int i = 0; i < listingsNumber; i++) {
            Listing listing = new Listing(
                    id++,
                    randString("listing_title_"),
                    null
            );
            LOG.debug("{}", listing);
            generatedChannels.add(listing);
        }
        return generatedChannels;
    }
}
