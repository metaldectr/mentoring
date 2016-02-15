package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;

import java.util.ArrayList;
import java.util.List;

import static com.romario.mentoring.util.RandomUtil.randInt;
import static com.romario.mentoring.util.RandomUtil.randString;
import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ChannelWriterExecutor class
 */
public class ChannelWriterExecutor extends AbstractCacheExecutor {
    public static int id = 0;
    public static final int MIN = 1;
    public static final int MAX = 5;

    public ChannelWriterExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        int channelsNumber = randInt(MIN, MAX);
        cache.addChannels(createChannels(channelsNumber));
        LOG.info("Generate {} channels. Cache size is {}", channelsNumber, cache.getCacheSize());
        sleepFor(5, SECONDS);
    }

    private List<Channel> createChannels(final int channelsNumber) {
        final List<Channel> generatedChannels = new ArrayList<Channel>();

        for (int i = 0; i < channelsNumber; i++) {
            Channel channel = new Channel(
                    id++,
                    randString("channel_title_"),
                    randString("channel_description_")
            );
            LOG.debug("+ {}", channel);
            generatedChannels.add(channel);
        }
        return generatedChannels;
    }
}
