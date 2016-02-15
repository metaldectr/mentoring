package com.romario.mentoring.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random util class
 */
public class RandomUtil {
    private static Random rand = new Random();

    public static int randInt(int max) {
        return randInt(0, max);
    }

    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String randString(String template) {
        String suffix = String.valueOf(System.nanoTime()).substring(6);
        return template + suffix;
    }

    public static long randLong(long min, long max) {
        long randomNum = ThreadLocalRandom.current().nextLong(min, max);
        return randomNum;
    }

    public static int nextInt() {
        return rand.nextInt();
    }

    public static long nextLong() {
        return rand.nextLong();
    }

}
