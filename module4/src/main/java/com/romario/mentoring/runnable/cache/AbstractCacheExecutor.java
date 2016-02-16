package com.romario.mentoring.runnable.cache;

import com.romario.mentoring.model.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCacheExecutor extends Thread {
    protected final Cache cache = Cache.getInstance();
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public AbstractCacheExecutor(String name) {
        super(name);
        setPriority(MIN_PRIORITY);
    }

    public void run() {
        LOG.info("Start");
        while (true) doThis();
    }

    protected abstract void doThis();
}
