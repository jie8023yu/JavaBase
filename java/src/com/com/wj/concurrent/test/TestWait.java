package com.com.wj.concurrent.test;

/**
 *
 */
public class TestWait {

    public static void main(String[] args) throws Exception{
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " start ");
                        lock.wait(1);
                        System.out.println(Thread.currentThread().getName() + " wait 10 ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " end ");
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " start ");
                    lock.notify();
                    System.out.println(Thread.currentThread().getName() + " notify ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

}

