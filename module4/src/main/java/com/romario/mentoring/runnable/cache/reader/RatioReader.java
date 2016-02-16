package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Ratio;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.*;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * RatioReader class
 */
public class RatioReader extends AbstractCacheExecutor {

    public static final int LISTINGS_TO_PRINT = 1;

    public RatioReader(String name) {
        super(name);
    }

    /**
     * Wrapper class
     */
    private static final class Info implements Comparable {
        public final Channel channel;
        public final Listing listing;
        public final Ratio ratio;

        public Info(Channel channel, Listing listing, Ratio ratio) {
            this.channel = channel;
            this.listing = listing;
            this.ratio = ratio;
        }

        public int compareTo(Object o) {
            Info other = (Info) o;
            int tmpResult = Long.compare(other.ratio.getValue(), this.ratio.getValue());
            return (tmpResult == 0)
                    ? Long.compare(other.channel.getId(), this.channel.getId())
                    : tmpResult;
        }
    }

    @Override
    protected void doThis() {
        List<Channel> channels = cache.getChannels();

        SortedSet<Info> descRatioInfo = getSortedInfo(channels);

        int printed = 0;
        for (Info info : descRatioInfo) {
            String channelTitle = info.channel.getTitle();
            String listingTitle = info.listing.getTitle();
            long ratioValue = info.ratio.getValue();
            LOG.info("{ channelTitle: '{}', listingTitle: '{}', ratioValue: {} }",
                    channelTitle, listingTitle, ratioValue);

            if (LISTINGS_TO_PRINT <= ++printed) break;
        }

        sleepFor(1, SECONDS);
    }

    private SortedSet<Info> getSortedInfo(List<Channel> channels) {
        SortedSet<Info> sortedInfo = new TreeSet<Info>();
        for (Channel channel : channels) {
            for (Listing listing : channel.getListings()) {
                Ratio ratio = listing.getRatio();
                if(ratio != null) {
                    sortedInfo.add(new Info(channel, listing, ratio));
                }
            }
        }
        return sortedInfo;
    }
}
