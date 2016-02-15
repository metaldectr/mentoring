package com.romario.mentoring.model.cache;

import java.util.*;

/**
 * Cache model class
 */
public class Cache {
    private static final Cache INSTANCE = new Cache();
    private final Map<Long, Channel> storage;

    private Cache() {
        storage = Collections.synchronizedMap(new HashMap<Long, Channel>());
    }

    public static Cache getINSTANCE() {
        return INSTANCE;
    }

    public List<Channel> getChannels() {
        return new ArrayList<Channel>(storage.values());
    }

    public void addChannel(final Channel channel) {
        this.storage.put(channel.getId(), channel);
    }
    public void addChannels(final List<Channel> channels) {
        Map<Long, Channel> tmp = new HashMap<Long, Channel>();
        for (Channel channel : channels) {
            tmp.put(channel.getId(), channel);
        }

        this.storage.putAll(tmp);
    }

    public int getCacheSize() {
        return this.storage.size();
    }


}
