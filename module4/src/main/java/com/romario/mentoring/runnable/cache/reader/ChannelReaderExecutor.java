package com.romario.mentoring.runnable.cache.reader;

import com.romario.mentoring.model.cache.Channel;
import com.romario.mentoring.model.cache.Cache;
import com.romario.mentoring.runnable.cache.AbstractCacheExecutor;
import org.apache.log4j.Logger;

import java.util.List;

import static com.romario.mentoring.util.PrintUtil.$;
import static com.romario.mentoring.util.ThreadUtil.*;

/**
 * ChannelReaderExecutor class
 */
public class ChannelReaderExecutor extends AbstractCacheExecutor {

    public ChannelReaderExecutor(String name) {
        super(name);
    }

    @Override
    protected void doThis() {
        sLogger.info("Run");
        List<Channel> channels = cache.getChannelList();

        for (Channel channel : channels) {
            long channelId = channel.getId();
            String channelTitle = channel.getTitle();
            String channelDescription = channel.getDesc();
            sLogger.info($("{ chanelID: '%s', chanelTitle: '%s', channelDescription: '%s' }",
                    channelId, channelTitle, channelDescription));
        }

        sleepFor(3, SECONDS);
    }
}
