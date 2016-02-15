package com.romario.mentoring.runnable.deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadLockThread extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(DeadLockThread.class);
    private Object resource1;
    private Object resource2;

    public DeadLockThread(String name) {
        super(name);
        setPriority(MIN_PRIORITY);
    }

    public DeadLockThread with(final Object resource1, final Object resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
        return this;
    }

    @Override
    public void run() {
        if (resource1 == null || resource2 == null) {
            throw new IllegalStateException("Thread not initialized");
        }

        synchronized (resource1) {
            LOG.info("Locked on: {}", resource1);
            try {
                Thread.sleep(50);
                synchronized (resource2) {
                    LOG.info("Locked on: {}", resource2);
                }
            } catch (InterruptedException ignore) {
            }
        }
    }
}
