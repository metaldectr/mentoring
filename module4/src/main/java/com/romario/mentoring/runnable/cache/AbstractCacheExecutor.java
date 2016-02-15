package com.romario.mentoring.runnable.cache;

import com.romario.mentoring.model.cache.Cache;
import org.apache.log4j.Logger;

public abstract class AbstractCacheExecutor extends Thread {
    protected static final Cache cache = Cache.getInstance();
    protected final Logger sLogger = Logger.getLogger(this.getClass());

    public AbstractCacheExecutor(String name) {
        super(name);
    }

    public void run() {
        sLogger.info("Start");
        while (true) doThis();
    }

    protected abstract void doThis();
}
