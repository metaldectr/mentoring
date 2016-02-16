package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.*;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ListingReader class
 */
public class ListingReader extends AbstractCacheExecutor {

    public static final int CHANNEL_TO_PRINT = 1;

    public ListingReader(String name) {
        super(name);
    }

    /**
     * Wrapper class
     */
    private static final class AverageRatioInfo implements Comparable {
        public final Channel channel;
        public final long avgRatio;

        public AverageRatioInfo(Channel channel, long avgRatio) {
            this.channel = channel;
            this.avgRatio = avgRatio;
        }

        public int compareTo(Object o) {
            AverageRatioInfo other = (AverageRatioInfo) o;
            int tmpResult = Long.compare(other.avgRatio, this.avgRatio);
            return (tmpResult == 0)
                    ? Long.compare(other.channel.getId(), this.channel.getId())
                    : tmpResult;
        }
    }

    @Override
    protected void doThis() {
        List<Channel> channels = cache.getChannels();
        SortedSet<AverageRatioInfo> avgRatioInfo = getAvgRatioInfo(channels);

        int printed = 0;
        for (AverageRatioInfo info : avgRatioInfo) {
            long channelID = info.channel.getId();
            long avgRatio = info.avgRatio;
            LOG.info("{ channelID: {}, averageRatio: {} }", channelID, avgRatio);
            if (CHANNEL_TO_PRINT <= ++printed) break;
        }

        sleepFor(1, SECONDS);
    }

    private SortedSet<AverageRatioInfo> getAvgRatioInfo(final List<Channel> channels) {
        SortedSet<AverageRatioInfo> sortedInfo = new TreeSet<AverageRatioInfo>();
        for (Channel channel : channels) {
            List<Listing> listings = channel.getListings();
            long averageRatio = calculateAverage(listings);
            sortedInfo.add(new AverageRatioInfo(channel, averageRatio));
        }
        return sortedInfo;
    }


    private long calculateAverage(final List<Listing> listings) {
        long count = 0;
        long sum = 0;
        for (Listing listing : listings) {
            if (listing != null && listing.getRatio() != null) {
                sum += listing.getRatio().getValue();
                count++;
            }
        }
        return (count != 0) ? (sum / count) : 0;
    }



}
