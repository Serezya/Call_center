package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Specialist extends Thread {
    private final BlockingQueue<String> calls;
    private final Ats ats;

    int timeHandling = 4000;

    public Specialist(BlockingQueue<String> calls, Ats ats) {
        this.calls = calls;
        this.ats = ats;
    }

    public void run() {
        while (true) {
            try {
                String numberCall = ats.waitNextCall();
                if(numberCall == null){
                    continue;
                }
                System.out.println(Thread.currentThread().getName() + " принял звонок от: " + numberCall);
                TimeUnit.MILLISECONDS.sleep(timeHandling);
                System.out.println(Thread.currentThread().getName() + " обработал звонок от: " + numberCall);
                System.out.println("Осталось обработать: " + calls.size() + " звонков");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
