package com.romario.mentoring.runnable.cache.writer;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import com.romario.mentoring.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

import static com.romario.mentoring.util.ThreadUtil.SECONDS;
import static com.romario.mentoring.util.ThreadUtil.sleepFor;

/**
 * ChannelWriterExecutor class
 */
public class ChannelWriterExecutor extends AbstractCacheExecutor {

    public static final int MIN = 1;
    public static final int MAX = 2;

    public ChannelWriterExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        sLogger.info("Run");
        List<Channel> tmpChannels = new ArrayList<Channel>();
        int count = RandomUtil.randInt(MIN, MAX);
        for (int i = 0; i < count; i++) {
            tmpChannels.add(new Channel(
                    RandomUtil.randInt(1, 500),
                    "channel:" + RandomUtil.nextInt(),
                    "description:" + RandomUtil.nextInt(),
                    null));
        }
        cache.setChannelList(tmpChannels);
        sleepFor(6, SECONDS);
    }
}
