package com.com.wj.concurrent.test;

/**
 * volatile只能保证可见性，而不能去保证原子性
 * 锁可以保证可见性和原子性
 */
public class Demo05 {

    private static volatile int sum = 0;

    public synchronized void increment() {
        sum++;
    }

    public static void main(String[] args) throws InterruptedException {
        final Demo05 d = new Demo05();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 100000 ; i++) /*sum = sum + 1;*/d.increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 100000 ; i++) /*sum += 1;*/ d.increment();
            }
        });
        t1.start();
        t2.start();


        t1.join();
        t2.join();


        System.out.println(sum);
    }


}
