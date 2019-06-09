package com.com.wj.concurrent.test.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(6);

    private static class InitThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getId() + " init run");
            countDownLatch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getId() + " init run");
            }
        }
    }

    private static class BusiThread extends Thread {

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getId() + " busi run");
                for (int i = 0; i < 2; i++) {
                    System.out.println(Thread.currentThread().getId() + " busi run");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //单独的初始化工程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                    System.out.println(Thread.currentThread().getId() + " step1");
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getId() + " step2");
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getId() + " step3");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new BusiThread().start();
        for (int i = 0; i < 3; i++) {
            new InitThread().start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getId() + " main start");
    }

}
