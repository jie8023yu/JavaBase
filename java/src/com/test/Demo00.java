package com.test;

import java.util.concurrent.ForkJoinPool;

public class Demo00 {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(2);

        new Thread(() -> {
            for (int i = 0; i < 1; i++) {
                pool.execute(() -> {
                    System.out.println("1");
                });
                System.out.println("execute over......");
            }
        }).start();

        pool.execute(() -> {
            System.out.println("1");
        });
        Thread.sleep(100);
        System.out.println("begin commit task");
        int i = 1;
        System.out.println("begin commit task2");
        pool.execute(() -> {
            System.out.println("1");
        });



    }




}
