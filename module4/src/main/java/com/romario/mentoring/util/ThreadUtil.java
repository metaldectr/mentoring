package com.romario.mentoring.util;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {
    public static final TimeUnit MILLISECONDS = TimeUnit.MILLISECONDS;
    public static final TimeUnit SECONDS = TimeUnit.SECONDS;
    public static final TimeUnit MINUTES = TimeUnit.MINUTES;


    public static void sleepFor(int time, TimeUnit unit){
        try {
            Thread.sleep(unit.toMillis(time));
        } catch (InterruptedException ignore) {}
    }
}
