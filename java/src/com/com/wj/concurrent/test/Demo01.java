package com.com.wj.concurrent.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Demo01 {
    
    private Object lock = new Object();
    private boolean flag = true;

    public static void main(String[] args) throws Exception {
        final Demo01 demo01 = new Demo01();
        Wait wait = demo01.new Wait();
        Notify notify = demo01.new Notify();
        new Thread(wait).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(notify).start();
    }
    

    class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        lock.wait(100);
                        System.out.println(Thread.currentThread().getName() + "," + new Date() + ",flag = " + flag);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "," + new Date() + ",flag = " + flag);
            }

        }
    }

    class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "," + new Date());
//                lock.notifyAll();
                flag = false;
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "," + new Date());
            }
        }
    }
    
}
