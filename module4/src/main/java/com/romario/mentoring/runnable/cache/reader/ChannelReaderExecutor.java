package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ChannelReaderExecutor class
 */
public class ChannelReaderExecutor extends AbstractCacheExecutor {

    public ChannelReaderExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        List<Channel> channels = cache.getChannels();

        LOG.info("Read {} channels. Cache size is {}", channels.size(), cache.getCacheSize());

        for (Channel channel : channels) {
            LOG.debug("- {}", channel);
        }

        sleepFor(5, SECONDS);
    }
}
