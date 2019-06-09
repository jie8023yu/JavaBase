package com.com.wj.concurrent.test.cyclicbarrier;

import org.junit.runner.RunWith;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    private static CyclicBarrier barrier = new CyclicBarrier(5);


    private static ConcurrentHashMap<String,Long> resultMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new SubThread()).start();
        }
    }

    private static class CollectThread implements Runnable {

        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String,Long> workResult : resultMap.entrySet()) {
                result.append("[" + workResult.getValue() + "]");
            }
            System.out.println(" the result = " + result);
            System.out.println(" do other business.....");
        }
    }

    private static class SubThread implements Runnable {

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "",id);
            Random r = new Random();
            try {
                if (r.nextBoolean()) {
                    Thread.sleep(1000 + id);
                    System.out.println("Thread_" + id + "...do something");
                }
                System.out.println(id + " wait");
                barrier.await();
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + " do its business");
            } catch (Exception e) {

            }

        }
    }
}
