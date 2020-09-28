package org.example;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Ats {
    BlockingQueue<String> calls;
    int countNewCalls;
    int pauseTime = 1000;

    public Ats(BlockingQueue<String> calls, int countNewCalls) {
        this.calls = calls;
        this.countNewCalls = countNewCalls;
    }

    public void newCall() {
        int i = 0;
        while (i < countNewCalls) {
            try {
                calls.put(getNumber());
                TimeUnit.MILLISECONDS.sleep(pauseTime);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getNumber() {
        return "+7 (" + getRandPartNumber(900, 999) + ") " +
                getRandPartNumber(900, 999) + " " +
                getRandPartNumber(10, 99) + "-" +
                getRandPartNumber(10, 99);
    }

    public int getRandPartNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
