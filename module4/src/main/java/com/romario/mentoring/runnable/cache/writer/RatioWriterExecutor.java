package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Ratio;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import com.romario.mentoring.util.RandomUtil;

import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * RatioWriterExecutor class
 */
public class RatioWriterExecutor extends AbstractCacheExecutor {

    public RatioWriterExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        LOG.info("Run");
        List<Channel> channels = cache.getChannels();
        for (Channel channel : channels) {
            List<Listing> listings = channel.getListings();
            if (listings != null && !listings.isEmpty()) {
                for (int i = 0; i < listings.size(); i++) {
                    Listing listing = listings.get(i);
                    if (listing != null) {
                        if (listing.getRatio() != null) {
                            Ratio ratio = listing.getRatio();
                            listings.add(
                                    i, new Listing(listing.getId(),
                                            listings.get(i).getTitle(),
                                            new Ratio(ratio.getListingId(), ratio.getValue())));
                        } else {
                            listings.add(
                                    i, new Listing(listing.getId(),
                                            listing.getTitle(),
                                            new Ratio(
                                                    RandomUtil.nextLong(), RandomUtil.nextLong())));
                        }
                    }
                }
            }
            cache.addChannels(channels);
        }
        sleepFor(1, SECONDS);
    }
}
