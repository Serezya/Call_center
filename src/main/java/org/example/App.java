package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) {
        int countNewCalls = 60;
        int countSpecialists = 4;

        BlockingQueue<String> calls = new ArrayBlockingQueue<>(countNewCalls);

        Ats ats = new Ats(calls, countNewCalls);
        new Thread(ats::incomeCall, "АТС").start();

        Specialist spec = new Specialist(calls, ats);
        for (int i = 1; i <= countSpecialists; i++) {
            new Thread(spec, "Специалист " + i).start();
        }
    }
}
