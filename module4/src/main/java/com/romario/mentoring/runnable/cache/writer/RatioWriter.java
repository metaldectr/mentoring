package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Ratio;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * RatioWriter class
 */
public class RatioWriter extends AbstractCacheExecutor {

    public static final int INIT_RATIO = 1;
    private static long id;

    public RatioWriter(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        for (Channel channel : cache.getChannels()) {
            List<Listing> updatedListings = new ArrayList<Listing>();
            for (Listing listing : channel.getListings()) {
                Listing updatedListing = updateListingRatio(listing);
                updatedListings.add(updatedListing);
            }
            LOG.info("{ channelID: {}, updatedListings: {} }", channel.getId(), updatedListings.size());
            cache.putChannel(new Channel(
                    channel.getId(),
                    channel.getTitle(),
                    channel.getDesc(),
                    updatedListings
            ));
        }

        sleepFor(1,SECONDS);
    }

    private Listing updateListingRatio(final Listing listing) {
        Ratio ratio = listing.getRatio();
        Ratio updatedRatio = (ratio == null)
                ? new Ratio(id++, INIT_RATIO)
                : new Ratio(ratio.getId(), ratio.getValue() + 1);
        return new Listing(listing.getId(), listing.getTitle(), updatedRatio);
    }
}
