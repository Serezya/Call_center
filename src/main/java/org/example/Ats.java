package org.example;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Ats {
    private final BlockingQueue<String> calls;
    private final int countNewCalls;
    int pauseTime = 1000;

    public Ats(BlockingQueue<String> calls, int countNewCalls) {
        this.calls = calls;
        this.countNewCalls = countNewCalls;
    }

    protected void incomeCall() {
        for (int i = 0; i < countNewCalls; i++) {
            try {
                calls.put(getNumber());
                TimeUnit.MILLISECONDS.sleep(pauseTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String waitNextCall() throws InterruptedException {
        String callNumber;
        while(true) {
            if(calls.size()==0) {
                Thread.sleep(pauseTime);
            }
            else {
                callNumber = calls.poll();
                break;
            }
        }
        return callNumber;
    }

    private String getNumber() {
        return "+7 (" + getRandPartNumber(900, 999) + ") " +
                getRandPartNumber(900, 999) + " " +
                getRandPartNumber(10, 99) + "-" +
                getRandPartNumber(10, 99);
    }

    private int getRandPartNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
