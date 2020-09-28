package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Specialist {
    BlockingQueue<String> calls;
    Ats ats;
    int timeHandling = 3000;

    public Specialist(BlockingQueue<String> calls, Ats ats) {
        this.calls = calls;
        this.ats = ats;
    }

    public void handllingCall() {
        while (!calls.isEmpty()) {
            try {
                String numberCall = ats.calls.poll();
                System.out.println(Thread.currentThread().getName() + " принял звонок от: " + numberCall);
                TimeUnit.MILLISECONDS.sleep(timeHandling);
                System.out.println(Thread.currentThread().getName() + " обработал звонок от: " + numberCall);
                System.out.println("Осталось обработать: " + ats.calls.size() + " звонков");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
