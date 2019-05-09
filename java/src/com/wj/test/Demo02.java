package com.wj.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {

    Lock lock = new ReentrantLock();

    int i = 0;

    public void incr() {
        try {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final Demo02 d = new Demo02();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 3;i++) {
                    d.incr();
                }
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 3;i++) {
                    d.incr();
                }
            }
        },"t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 3;i++) {
                    d.incr();
                }
            }
        },"t3");



        t1.start();
        t2.start();
//        t3.start();

    }

}
