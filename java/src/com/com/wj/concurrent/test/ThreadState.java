package com.com.wj.concurrent.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadState {

    volatile  int test;

    ReentrantLock lock;


    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();

    }

    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            while (true) {

            }
        }
    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
