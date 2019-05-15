package com.com.wj.concurrent.test;

import java.util.concurrent.TimeUnit;

public class Shutdown {


    public static void main(String[] args) throws InterruptedException {
       Runner one = new Runner();
       Thread countThread = new Thread(one,"CountThread");
       countThread.start();
       //睡眠1s，main线程对CountThread线程进行中断，使CountThread能够感知中断而结束
       TimeUnit.SECONDS.sleep(1);
       countThread.interrupt();
       Runner two = new Runner();
       countThread = new Thread(two,"CountThread");
       countThread.start();
       //同上，让线程能够感知到on为false而结束
       TimeUnit.SECONDS.sleep(1);
       two.cancel();

    }

    public static class Runner implements Runnable {

        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }

}
