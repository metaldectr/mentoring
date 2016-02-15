package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import com.romario.mentoring.util.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ListingReaderExecutor class
 */
public class ListingReaderExecutor extends AbstractCacheExecutor {

    public ListingReaderExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        List<Channel> channels = cache.getChannels();
        Map<Channel, Long> channelsMap = calculateAverageRatio(channels);
        LOG.info("Read {} channels. Cache size is {}", channels.size(), cache.getCacheSize());
        for (Map.Entry<Channel, Long> entry : channelsMap.entrySet()) {
            long channelId = entry.getKey().getId();
            long averageRation = entry.getValue();
            LOG.debug("ListingReaderResult { chanelID: '{}', averageRatio: '{}' }", channelId, averageRation);
        }

        sleepFor(3, SECONDS);
    }

    private Map<Channel, Long> calculateAverageRatio(final List<Channel> channels) {
        Map<Channel, Long> averageRatioMap = new HashMap<Channel, Long>();
        for (Channel channel : channels) {
            List<Listing> listings = channel.getListings();
            if (CollectionUtil.isNotEmpty(listings)) {
                long averageRatio = calculateAverage(listings);
                averageRatioMap.put(channel, averageRatio);
            }
        }
        return averageRatioMap;
    }


    private long calculateAverage(final List<Listing> listings) {
        long count = 0;
        long sum = 0;
        if (CollectionUtil.isNotEmpty(listings)) {
            for (Listing listing : listings) {
                if (listing != null && listing.getRatio() != null) {
                    sum += listing.getRatio().getValue();
                    count++;
                }
            }
        }
        return (count != 0) ? (sum / count) : 1;
    }

}
