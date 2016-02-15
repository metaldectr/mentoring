package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Ratio;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

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
        sLogger.info("Run");
        List<Channel> channels = cache.getChannelList();
        for (Channel channel : channels) {
            List<Listing> listings = channel.getListing();
            if (listings != null && !listings.isEmpty()) {
                for (int i = 0; i < listings.size(); i++) {
                    Listing listing = listings.get(i);
                    if (listing != null) {
                        if (listing.getRatio() != null) {
                            Ratio ratio = listing.getRatio();
                            listings.add(
                                    i, new Listing(listing.getId(), listing.getChannelId(),
                                            listings.get(i).getTitle(),
                                            new Ratio(ratio.getListingId(), ratio.getRatio())));
                        } else {
                            listings.add(
                                    i, new Listing(listing.getId(), listing.getChannelId(),
                                            listing.getTitle(),
                                            new Ratio(
                                                    RandomUtil.nextLong(), RandomUtil.nextLong())));
                        }
                    }
                }
            }
            cache.setChannelList(channels);
        }
        sleepFor(1, SECONDS);
    }
}
