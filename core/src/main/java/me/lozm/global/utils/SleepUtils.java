package me.lozm.global.utils;

import java.util.Random;

public class SleepUtils {

    public static void sleepThread(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomInteger(int bound) {
        return new Random().nextInt(bound);
    }

}
