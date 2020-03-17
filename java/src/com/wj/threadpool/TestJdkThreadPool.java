package com.wj.threadpool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestJdkThreadPool {


    public static void main(String[] args) {
        testSingleThreadPool();
//        testCachePool();

//        testScheduledThreadPool();
    }


    public static void testSingleThreadPool() {
        //只有一个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0 ; i < 5 ; i++) {
            final int j = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(j + "-" + Thread.currentThread().getName());
            });
        }
    }

    public static void testCachePool() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < 2 ; i++) {
            final int j = i;
            executorService.execute(() -> {
                System.out.println(j + "-" + Thread.currentThread().getName());
            });
        }

        System.out.println(executorService);

        try {
            TimeUnit.SECONDS.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(executorService);

    }

    public static void testFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
    }

    public static void testScheduledThreadPool() {
        ExecutorService executorService = Executors.newScheduledThreadPool(4);
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500,TimeUnit.MILLISECONDS);

    }
}
