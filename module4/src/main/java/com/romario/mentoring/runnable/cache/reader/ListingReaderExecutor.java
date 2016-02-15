package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.romario.mentoring.util.PrintUtil.$;
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
        LOG.info("Run");
        List<Channel> channels = cache.getChannels();
        Map<Long, Channel> channelsMap = calculateAverageRatio(new ArrayList<Channel>(channels));
        SortedSet<Long> keys = new TreeSet<Long>(channelsMap.keySet());
        if (keys.size() > 2) {
            Iterator<Long> iterator = keys.iterator();
            for (int i = 0; i < 3; i++) {
                if (iterator.hasNext()) {
                    Long key = iterator.next();
                    String channelTitle = channelsMap.get(key).getTitle();
                    String channelDescription = channelsMap.get(key).getDesc();
                    LOG.info($("{ chanelTitle: '%s', channelDescription: '%s' }",
                            channelTitle, channelDescription));
                }
            }
        }

        sleepFor(3, SECONDS);
    }

    private Map<Long, Channel> calculateAverageRatio(List<Channel> channels) {
        Map<Long, Channel> averageRatioMap = new HashMap<Long, Channel>();
        for (Channel channel : channels) {
            if (channel.getListings() != null) {
                if (channel.getListings() != null && !channel.getListings().isEmpty()) {
                    long averageRatio = calculateAverage(new ArrayList<Listing>(channel.getListings()));
                    averageRatioMap.put(averageRatio, channel);
                }
            }
        }
        return averageRatioMap;
    }


    private long calculateAverage(List<Listing> listings) {
        long count = 0;
        long sum = 0;
        if (listings != null && !listings.isEmpty()) {
            for (Listing listing : listings) {
                if (listing != null && listing.getRatio() != null) {
                    sum += listing.getRatio().getValue();
                    count++;
                }
            }
        }

        if (count != 0) {
            return sum / count;
        } else {
            return 1;
        }
    }

}
