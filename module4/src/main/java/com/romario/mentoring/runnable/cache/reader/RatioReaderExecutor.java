package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Listing;
import com.romario.mentoring.model.cache.Ratio;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.romario.mentoring.util.PrintUtil.$;
import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * RatioReaderExecutor class
 */
public class RatioReaderExecutor extends AbstractCacheExecutor {

    public RatioReaderExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        LOG.info("Run");
        List<Channel> channels = new ArrayList<Channel>(cache.getChannels());
        List<Ratio> ratioList = new ArrayList<Ratio>();

        for (Channel channel : channels) {
            if (channel.getListings() != null) {
                for (Listing listing : new ArrayList<Listing>(channel.getListings())) {
                    ratioList.add(listing.getRatio());
                }
            }
        }

        List<Ratio> topRatioList = getTopRatioList(new ArrayList<Ratio>(ratioList));
        for (int i = 0; i < 10; i++) {
            if (topRatioList != null && !topRatioList.isEmpty() &&
                    topRatioList.size() > 10 && topRatioList.get(i) != null) {
                long listingId = topRatioList.get(i).getListingId();
                Channel channel = findChannelById(channels, listingId);
                Listing listing = findListingById(channels, listingId);
                if (channel != null && listing.getRatio() != null) {
                    String channelTitle = channel.getTitle();
                    String listingTitle = listing.getTitle();
                    long ratio = listing.getRatio().getValue();
                    LOG.info($("{ chanelTitle: '%s', listingTitle: '%s', ratio: '%s' }",
                            channelTitle, listingTitle, ratio));
                }
            }
        }

        sleepFor(2, SECONDS);
    }

    private Listing findListingById(List<Channel> channelList, long listingId) {
        for (Channel channel : channelList) {
            if (channel.getListings() != null && !channel.getListings().isEmpty()) {
                for (Listing listing : channel.getListings()) {
                    if (listing.getId() == listingId) {
                        return listing;
                    }
                }
            }
        }
        return null;
    }

    private Channel findChannelById(List<Channel> channelList, long listingId) {
        for (Channel channel : channelList) {
            if (channel.getListings() != null && !channel.getListings().isEmpty()) {
                for (Listing listing : channel.getListings()) {
                    if (listing.getId() == listingId) {
                        return channel;
                    }
                }
            }
        }
        return null;
    }

    private List<Ratio> getTopRatioList(List<Ratio> ratioList) {
        Collections.sort(ratioList, new Comparator<Ratio>() {
                    public int compare(Ratio o1, Ratio o2) {
                        if (o1 == null || o2 == null) {
                            return 0;
                        }
                        if (o1.getValue() < o2.getValue()) {
                            return -1;
                        } else if (o1.getValue() > o2.getValue()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
        );
        return ratioList;
    }


}
