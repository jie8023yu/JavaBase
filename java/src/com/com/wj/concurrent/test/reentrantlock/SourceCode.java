package com.com.wj.concurrent.test.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SourceCode {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
//            try {
//                Thread.sleep(10000);
//                lock.unlock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();


        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            lock.unlock();
        }).start();

//        lock.unlock();

        new Thread(() -> {
            lock.lock();
            lock.unlock();
        }).start();
    }
}
