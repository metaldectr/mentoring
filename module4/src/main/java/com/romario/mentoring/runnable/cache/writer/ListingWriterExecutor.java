package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import com.romario.mentoring.util.RandomUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ListingWriterExecutor class
 */
public class ListingWriterExecutor extends AbstractCacheExecutor {

    public ListingWriterExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        sLogger.info("Run");
        List<Channel> channels = new ArrayList<Channel>(cache.getChannelList());
        int countIter = channels.size();
        for (int i = 0; i < countIter; i++) {
            Channel channel = channels.get(i);
            int count = RandomUtil.randInt(1, 2);
            List<Listing> tmpListings = channel.getListing();
            for (int j = 0; j < count; j++) {
                if (tmpListings == null) {
                    tmpListings = new ArrayList<Listing>(count);
                }
                tmpListings.add(
                        new Listing(RandomUtil.randInt(1, 3), RandomUtil.nextLong(),
                                "listingTitle:" + RandomUtil.nextInt(), null));
            }

            channel.setListing(tmpListings);
        }
        cache.setChannelList(channels);
        sleepFor(5, SECONDS);
    }
}
