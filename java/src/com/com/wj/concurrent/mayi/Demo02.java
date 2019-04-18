package com.com.wj.concurrent.mayi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {

    ReentrantLock reentrantLock = new ReentrantLock();
    private int i;

    public void incr() {
        try {
            reentrantLock.lock();
            i++;
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            reentrantLock.unlock();
        }

    }

    public static void main(String[] args) {
        final Demo02 d = new Demo02();
        Thread thread = new Thread(() -> {
            for (int i = 0;i < 10;i++) {
                d.incr();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0;i < 10;i++) {
                d.incr();
            }
        });

        thread.start();
        thread2.start();
    }

}
