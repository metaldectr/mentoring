package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ChannelReader class
 */
public class ChannelReader extends AbstractCacheExecutor {

    public ChannelReader(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        List<Channel> channels = cache.getChannels();

        LOG.info("{ read: {}, total: {} }", channels.size(), cache.getCacheSize());
        for (Channel channel : channels) {
            LOG.debug("{}", channel);
        }

        sleepFor(5, SECONDS);
    }
}
